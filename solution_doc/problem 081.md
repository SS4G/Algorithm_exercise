## 81. Search in Rotated Sorted Array II


```
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?
```


Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.

#### tips
这个可以吧二分法改造一下 没啥太大的意思

#### mycode

```
class Solution(object):
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: bool
        """
        return target in set(nums)
```
