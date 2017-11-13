## 63. Unique Paths II

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.


```
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
```

The total number of unique paths is 2.

Note: m and n will be at most 100.

#### tips
延续上一个Leet062的思路 只是加了一个障碍五 如果一个格子的左边有障碍 那么左边的路径数量为0 同理 上边有障碍物也是 所以我们可以在扫描的过程中 吧障碍的路径数量设置为0 然后障碍物右边或者上边的格子直接加0 即可

#### mycode

```
class Solution(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """
        WAY = 0
        STONE = 1
        mat = obstacleGrid
        rL = len(mat)
        if rL == 0:
            return 0
        cL = len(mat[0])
        matRec = [[0, ] * cL for r in range(rL)]

        for c in range(cL):
            if mat[0][c] == WAY:
                if c == 0:
                    matRec[0][0] = 1
                else:
                    matRec[0][c] = matRec[0][c - 1]

        for r in range(rL):
            if mat[r][0] == WAY:
                if r == 0:
                    pass  # has checked before
                else:
                    matRec[r][0] = matRec[r - 1][0]

        for r in range(1, rL):
            for c in range(1, cL):
                if mat[r][c] == WAY:
                    matRec[r][c] = matRec[r - 1][c] + matRec[r][c - 1]
        return matRec[rL - 1][cL - 1]
```
