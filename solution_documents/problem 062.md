## 62. Unique Paths

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

![image](https://leetcode.com/static/images/problemset/robot_maze.png)

Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

#### tips
这个是一个很典型的动态规划， 根据每个格子的左和上的格子的路径数量 算出当前格子的路径数量是左格子和上格子之和。
对于边缘的 可以先计算 如上边缘 或者左边缘的所有格子的路径都为1

#### mycode

```
class Solution(object):
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        if m == 1 or n == 1:
            return 1
        elif m == 0 or n == 0:
            return 0
        rec = [[-1 for j in range(n)] for i in range(m)]
        for i in range(m):
            rec[i][0] = 1
        for j in range(n):
            rec[0][j] = 1
        for i in range(1, m):
            for j in range(1, n):
                rec[i][j] = rec[i][j - 1] + rec[i - 1][j]
        return rec[m - 1][n - 1]
```
