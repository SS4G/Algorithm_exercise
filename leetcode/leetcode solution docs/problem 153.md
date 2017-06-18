## 153. Find Minimum in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.

#### tips
这个数列有个规律

nums[0]>nums[-1]>lowest 所以可以稍微对二分查找的找到条件进行一下修改即可 具体见代码


```
。  3 
  5
4
         
       2
     1
```

#### mycode
```
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
```
