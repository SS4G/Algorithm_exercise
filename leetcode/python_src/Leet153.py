class Solution(object):
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        # return min(nums) # sure you can do this in O(1)
        lo = 0
        hi = len(nums) - 1
        if nums[lo] < nums[hi]:
            return nums[lo]
        while lo < hi:
            mid = (lo + hi) >> 1
            if nums[mid] > nums[hi]:
                lo = mid + 1
            elif nums[mid] < nums[hi]:
                hi = mid
        return nums[lo]

if __name__ == "__main__":
    s = Solution()
    print(s.findMin([4, 5, 6, 7, 0, 1, 2]))