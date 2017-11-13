## 462. Minimum Moves to Equal Array Elements II
Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

#### tips
求各个值到中位数的距离之和

见leetcode best meeting point的证明  数轴到所有点距离之和最小的地方时 AB之间 是这个问题证明的关键

```
C A B D
```
#### mycode

```
class Solution(object):
    def minMoves2(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums == []:
            return 0
        nums.sort()
        l = len(nums)
        mid = l >> 1
        midVal = nums[mid]
        return sum([abs(midVal - i) for i in nums])
```
