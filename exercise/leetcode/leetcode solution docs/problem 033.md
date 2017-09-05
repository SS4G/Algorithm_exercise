## 33. Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.


```
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
```


You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
#### tips
见leetcode153 先用二分法找到最小值的index 然后分段在最小值坐标两侧使用二分查找即可
#### mycode

```
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
```
