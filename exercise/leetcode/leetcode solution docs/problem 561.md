Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible. 

```
Example 1:
Input: [1,4,3,2]

Output: 4
```

Explanation: n is 2, and the maximum sum of pairs is 4.

Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].
#### tips
从小到大排序 取偶数索引上的值相加  从最小的一对开始想起
Consider the smallest element x. It should be paired with the next smallest element, because min(x, anything) = x, and having bigger elements only helps you have a larger score. Thus, we should pair adjacent elements together in the sorted array.
#### mycode
```Python
class Solution(object):
    def arrayPairSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums.sort()
        return sum(nums[0:len(nums)-1:2])
```
