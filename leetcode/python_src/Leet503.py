class Solution(object):
    def nextGreaterElements(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        if not nums:
            return []
        indexStack = []
        res = [-1, ]*len(nums)
        nextI = lambda x: x+1 if x < len(nums)-1 else 0
        max = -0xffffffff
        maxI = -1
        for i in range(len(nums)):
            maxI = i if nums[i] > max else maxI
            max = nums[i] if nums[i] > max else max
        i = nextI(maxI)
        indexStack.append(maxI)
        cyc = True
        while i != nextI(maxI) or cyc:
            cyc = False
            while nums[indexStack[-1]] < nums[i]:
                popedIndex = indexStack.pop()
                res[popedIndex] = nums[i]
            indexStack.append(i)
            i = nextI(i)
        return res

if __name__ == "__main__":
    s = Solution()
    l = [1,2,5,6,8,2,1,0]
    print(s.nextGreaterElements(l))
