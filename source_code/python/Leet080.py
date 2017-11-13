class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        dictx = {}
        for i in nums:
            if i not in dictx:
                dictx[i] = 1
            else:
                dictx[i] += 1

        cnt = 0
        sortedKeys = list(dictx.keys())
        sortedKeys.sort()
        j = 0
        for i in sortedKeys:
            if dictx[i] >= 2:
                cnt += 2
                nums[j] = i
                nums[j+1] = i
                j += 2
            else:
                nums[j] = i
                j += 1
                cnt += 1
        return cnt


if __name__ == "__main__":
    s = Solution()
    nums = [-1,0,0,0,0,3,3]
    print(s.removeDuplicates(nums))
    print(nums)

