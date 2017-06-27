class Solution(object):
    def minSubArrayLen(self, s, nums):
        """
        :type s: int
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return 0 if nums[0] >= s else 1
        lo = 0
        hi = 1
        curSum = nums[lo]
        curMinLen = 0x7fffffff
        foundFlag = False
        while hi < len(nums):
            if curSum < s:
                curSum += nums[hi]
                hi += 1
                if hi == len(nums) and curSum >= s:
                    foundFlag = True
                    while curSum - nums[lo] >= s:
                        curSum -= nums[lo]
                        lo += 1
                    curMinLen = min(hi - lo, curMinLen)
            else:
                foundFlag = True
                curMinLen = min(hi - lo, curMinLen)
                curSum -= nums[lo]
                lo += 1
        return curMinLen if foundFlag else 0

if __name__ == "__main__":
    s = Solution()
    nums = [4, 1]
    print(s.minSubArrayLen(7, nums))
