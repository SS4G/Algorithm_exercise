
# leetcode 453
## Question
#### Minimum Moves to Equal Array Elements
Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:


```
Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
```
## Ansewr
其实这个东西 给其他所有元素加一相对而言是给那个没有加的减一
所以 就是求出减去共有的基准值(原序列的最小值)后，数列的和

所以一行代码就够了
##### code(beat 40%)

```
class Solution(object):
    def minMoves(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        return sum(nums)-len(nums)*min(nums)
```
