class Solution:
    def checkPossibility(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        cnt = 0
        idx = -1
        for i in range(len(nums)):
            if i < len(nums) - 1 and nums[i] > nums[i + 1]:
                idx = i
                cnt += 1
        if cnt > 1:
            return False
        elif cnt == 0:
            return True
        else:
            return  idx == 0 or idx + 1 == len(nums) - 1 or nums[idx - 1] <= nums[idx + 1] or nums[idx] <= nums[idx + 2]

if __name__ == "__main__":
    s = Solution()
    nums = [1, 2, 3, 4]
    assert s.checkPossibility(nums)
    nums = [1, 4, 3]
    assert s.checkPossibility(nums)
    nums = [7, 4, 5]
    assert s.checkPossibility(nums)
    nums = [7, 48, 3]
    assert s.checkPossibility(nums)
