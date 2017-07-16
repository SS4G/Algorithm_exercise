## 64. Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

#### tips
这个题目算长度的方式和一个Triangle的题目 Leet120很像 照猫画虎吧

#### mycode


```
class Solution(object):
    def minPathSum(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        rL = len(grid)
        if rL == 0:
            return 0
        cL = len(grid[0])

        distaceRec = [[-1, ] * cL for i in range(rL)]
        for i in range(rL):
            if i == 0:
                distaceRec[0][0] = grid[0][0]
            else:
                distaceRec[i][0] = distaceRec[i - 1][0] + grid[i][0]

        for j in range(cL):
            if j == 0:
                pass
            else:
                distaceRec[0][j] = distaceRec[0][j - 1] + grid[0][j]

        for r in range(1, rL):
            for c in range(1, cL):
                distaceRec[r][c] = min(distaceRec[r - 1][c], distaceRec[r][c - 1]) + grid[r][c]

        return distaceRec[rL - 1][cL - 1]
```
