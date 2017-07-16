class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        preNot = 0
        preYes = nums[0]
        for i in range(1, len(nums)):
            curNot = max(preNot, preYes)
            curYes = preNot + nums[i]
            preNot = curNot
            preYes = curYes

        return max(preNot, preYes)

if __name__ == "__main__":
    s = Solution()
    nums = [1, 3, 2, 7, 3, 9, 100, 3, 20, 400]
    print(s.rob(nums))