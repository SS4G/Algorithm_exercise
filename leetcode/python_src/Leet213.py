class Solution(object):
    def rob(self, nums):
        if len(nums) == 0:
            return 0
        if len(nums) == 1:
            return nums[0]
        if len(nums) <= 3:
            return max(nums)

        # n - 1 not robbed
        preNot0 = 0
        preYes0 = nums[0]
        for i in range(1, len(nums) - 1):
            curNot0 = max(preNot0, preYes0)
            curYes0 = preNot0 + nums[i]
            preNot0 = curNot0
            preYes0 = curYes0

        max0 = max(preNot0, preYes0)

        preNot1 = 0
        preYes1 = nums[1]
        for i in range(2, len(nums)):
            curNot1 = max(preNot1, preYes1)
            curYes1 = preNot1 + nums[i]
            preNot1 = curNot1
            preYes1 = curYes1

        max1 = max(preNot1, preYes1)

        preNot2 = 0
        preYes2 = nums[1]
        for i in range(2, len(nums) - 1):
            curNot2 = max(preNot2, preYes2)
            curYes2 = preNot2 + nums[i]
            preNot2 = curNot2
            preYes2 = curYes2

        max2 = max(preNot2, preYes2)
        return max([max1, max2, max0])

if __name__ == "__main__":
    s = Solution()
    nums = [1, 300, 2, 7, 3, 9, 100, 3, 20, 400, 3]
    print(s.rob(nums))