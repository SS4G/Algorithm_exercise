class Solution(object):
    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        i = len(nums) - 1
        minIndex = i
        while i >= 0:
            if i + nums[i] >= minIndex:
                minIndex = i
            i -= 1
        return minIndex == 0

if __name__ == "__main__":
    s = Solution()
    nums0 = [2, 3, 1, 1, 4]
    nums1 = [3, 2, 1, 0, 4]
    nums2 = [0, ]
    nums3 = [1, 2]
    print(s.canJump(nums3))