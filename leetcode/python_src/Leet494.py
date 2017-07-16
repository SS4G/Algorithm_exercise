class Solution(object):
    def findTargetSumWays(self, nums, S):
        """
        :type nums: List[int]
        :type S: int
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        dict0 = {}
        res = self.findTargetRecursive(nums, len(nums) - 1, S, dict0)
        return res

    def findTargetRecursive(self, nums, startIndex, targetSum, recDict):
        if (startIndex, targetSum) in recDict:
            return recDict[(startIndex, targetSum)]
        if startIndex == 0:
            if targetSum == 0 and nums[0] == 0:
                res = 2
            elif nums[0] == targetSum or nums[0] == -targetSum:
                res = 1
            else:
                res = 0
        else:
            a = self.findTargetRecursive(nums, startIndex - 1, targetSum - nums[startIndex], recDict)
            b = self.findTargetRecursive(nums, startIndex - 1, targetSum + nums[startIndex], recDict)
            res = a + b
        recDict[(startIndex, targetSum)] = res
        return res

if __name__ == "__main__":
    s = Solution()
    nums = [0, 0]
    print(s.findTargetSumWays(nums, 0))
