class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        dp = [[None, None] for i in range(len(nums) + 1)]
        #  dp[i][0] maxium val if we don't rob this house
        #  dp[i][1] maxium val if we rob this house
        dp[0][0] = 0
        dp[0][1] = nums[0]
        for i in range(1, len(nums)):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
            dp[i][1] = dp[i - 1][0] + nums[i]
        return max(dp[len(nums) - 1][0], dp[len(nums) - 1][1])

if __name__ == "__main__":
    s = Solution()
    nums = [1, 3, 2, 7, 3, 9, 100, 3, 20, 400]
    print(s.rob(nums))