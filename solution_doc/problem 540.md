## 540. Single Element in a Sorted Array

Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

Example 1:

```
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
```

Note: Your solution should run in O(log n) time and O(1) space.

#### tips
本质上是一个变相的二分搜索， 即给出一个移动游标的条件来逐步索小平查找范围， 关于二分搜索退出循环的条件以及游标变化的条件主要是看具体问题不能一概而论

对于这个问题，是因为查找的对象只可能出现在偶数下标的位置 所有偶数下表位置的处理有一点特别 但是无论二分搜索的要求怎么变化最重要的一条就是，绝对不能让要查找的对象漏网 要拿lo 和hi把他框住

#### mycode

```
class Solution(object):
    def singleNonDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        lo = 0
        hi = len(nums) - 1
        while lo < hi:
            mid = (lo + hi) >> 1
            curVal = nums[mid]
            if mid % 2: # odd index
                if nums[mid - 1] != curVal:
                    hi = mid - 1
                else:
                    lo = mid + 1
            else: # even index
                if nums[mid + 1] != curVal:
                    hi = mid
                else:
                    lo = mid + 1
        # print(nums[lo])
        # print(nums[hi])
        return  nums[lo]
```
