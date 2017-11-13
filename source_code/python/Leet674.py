class Solution:
    def findLengthOfLCIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        state = False
        last = 0
        idx = 0
        maxLen = 0
        cnt = 0
        while idx < len(nums):
            #print(idx)
            if state is False:
                state = True
                last = nums[idx]
                cnt = 1
                idx += 1
            else:
                if nums[idx] > last:
                    last = nums[idx]
                    cnt += 1
                    idx += 1
                else:
                    state = False
                    maxLen = max(cnt, maxLen)
        maxLen = max(cnt, maxLen)
        return maxLen

if __name__ == "__main__":
    s = Solution()
    nums = [1, 2, 3, 4, 5, 1, 2, 3]
    print(s.findLengthOfLCIS(nums))
    assert s.findLengthOfLCIS(nums) == 5
    nums = [1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6]
    assert s.findLengthOfLCIS(nums) == 6
    nums = [1, 2, 3, 4]
    assert s.findLengthOfLCIS(nums) == 4
    nums = [1, 1, 1]
    assert s.findLengthOfLCIS(nums) == 1
    nums = [1,]
    assert s.findLengthOfLCIS(nums) == 1
    nums = []
    assert s.findLengthOfLCIS(nums) == 0
