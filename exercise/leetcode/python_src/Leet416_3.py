class Solution(object):
    """
    status : AC
    """
    def canPartition(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) <= 1:
            return False
        nums.sort()
        sum0 = sum(nums)
        if sum0 & 0x01 != 0:
            return False
        else:
            halfSum = sum0 >> 1
            dp = [[False, ] * (halfSum + 1) for i in range(len(nums))]

            for i in range(len(nums)):
                if nums[i] <= halfSum:
                    dp[i][nums[i]] = True
                dp[i][0] = True

            for n in range(1, len(nums)):
                i = halfSum
                while i >= nums[n]:
                    dp[n][i] = dp[n - 1][i] or dp[n - 1][i - nums[n]]
                    i -= 1
            return dp[len(nums) - 1][halfSum]

if __name__ == "__main__":
    s = Solution()
    nums = [1, 5, 11, 5]
    nums = [1, 2, 3, 5]
    nums = [41, 20, 99, 98, 50, 48, 64, 15, 74, 94,60,33,61,34,47,35,24,58,28,73,36,51,80,57,42,52,73,27,94,59,50,99,32,65,76,62,69,80,41,51,49,74,93,12,77,30,25,59,55,13,41,23,34,31,47,53,8,88,86,88,36,32,23,37,1,7,67,49,20,31,59,99,15,21,47,35,93,1,14,56,57,36,13,27,26,64,63,52,98,20,52,23,84,39,34,59,98,71,90,99]
    nums = [99, 2, 3, 98]
    nums = [1,2,5]
    r = s.canPartition(nums)
    print(r)

