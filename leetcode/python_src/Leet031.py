class Solution(object):
    def nextPermutation(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        if len(nums) <= 1:
            return
        i = len(nums) - 1
        allLessFlag = True
        while i >= 1:
            if nums[i] <= nums[i - 1]:
                i -= 1
            else:
                allLessFlag = False
                break
        if allLessFlag:
            nums.sort()
        else:
            part = nums[i - 1:]
            oldFirstNum = part[0]
            part.sort()
            newFirstNumIndex = 0
            for j in range(len(part)):
                if part[j] > oldFirstNum:
                    newFirstNumIndex = j
                    break
            tmp = part[newFirstNumIndex]
            part[newFirstNumIndex] = part[0]
            part[0] = tmp
            resPart = part[1:]
            resPart.sort()
            part[1:] = resPart[:]
            nums[i - 1:] = part[:]
            return

if __name__ == "__main__":
    s = Solution()
    print(s.nextPermutation([1, 2, 3, 3, 2]))





