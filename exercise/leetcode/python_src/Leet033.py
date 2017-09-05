class Solution(object):
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        if len(nums) == 0:
            return -1
        lowestIndex = self.findMin(nums)
        if lowestIndex == 0:
            return self.binarySearchInRange(nums, 0, len(nums) - 1, target)
        if nums[lowestIndex] <= target <= nums[-1]:
            return self.binarySearchInRange(nums, lowestIndex, len(nums) - 1, target)
        elif nums[0] <= target <= nums[lowestIndex-1]:
            return self.binarySearchInRange(nums, 0, lowestIndex-1, target)
        else:
            return -1

    def binarySearchInRange(self, nums, lo, hi, target):

        while lo <= hi:
            mid = (lo + hi) >> 1
            if nums[mid] == target:
                return mid
            elif nums[mid] > target:
                hi = mid - 1
            elif nums[mid] < target:
                lo = mid + 1
        return -1

    def findMin(self, nums):
        """
        see Leetcode 153
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
        return lo

if __name__ == "__main__":
    s = Solution()
    nums = [1, 2, 3, 4, 5, 6, 7]
    nums = [3, 1]
    print(s.search(nums, 1))
    #print(s.search(nums, 2))
    #print(s.search(nums, 8))
    #print(s.search(nums, 0))
    #nums = [6, 7, 8, 1, 2, 3, 4]
    #print(s.search(nums, 1))
    #print(s.search(nums, 7))
    #print(s.search(nums, 2))
    #print(s.search(nums, 5))