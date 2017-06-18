## 417. Pacific Atlantic Water Flow

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
- The order of returned grid coordinates does not matter.
- Both m and n are less than 150.  
Example:

Given the following 5x5 matrix:


```
Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic
```

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

#### tips
这个题就是一个简单的深度搜索， 从外侧的每个点开始 如果他周围的点的值比他高那么就说明周围的点可以到达当前点 就按照这个规则进行两次dfs搜索 最后检查结果即可

#### mycode

```
class Solution(object):
    def __init__(self):
        self.ATLANTIC = 1
        self.PACIFIC = 0


    def pacificAtlantic(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[List[int]]
        """
        lengthR = len(matrix)
        if lengthR == 0:
            return []
        lengthC = len(matrix[0])
        toAtlantic = [[-1 for j in range(lengthC)] for i in range(lengthR)]
        toPacific = [[-1 for j in range(lengthC)] for i in range(lengthR)]

        for i in range(lengthC):  # mark pacific
            self.dfs(matrix, 0, i, self.PACIFIC, toAtlantic, toPacific)
        for i in range(lengthR):  # mark pacific
            self.dfs(matrix, i, 0, self.PACIFIC, toAtlantic, toPacific)
        for i in range(lengthC):  # mark pacific
            self.dfs(matrix, lengthR-1, i, self.ATLANTIC, toAtlantic, toPacific)
        for i in range(lengthR):  # mark pacific
            self.dfs(matrix, i, lengthC-1, self.ATLANTIC, toAtlantic, toPacific)

        res = []
        for r in range(lengthR):
            for c in range(lengthC):
                if toPacific[r][c] != -1 and toAtlantic[r][c] != -1:
                    res.append((r, c))
        return res

    def dfs(self, mat, r, c, whichOcean, atlantic, pacific):
        if whichOcean == self.ATLANTIC:
            atlantic[r][c] = 3
        else:
            pacific[r][c] = 2
        adjs = self.getAdjance(r, c, len(mat), len(mat[0]))
        for a in adjs:
            if whichOcean == self.ATLANTIC:  # mark atlantic ocean
                if mat[a[0]][a[1]] >= mat[r][c] and atlantic[a[0]][a[1]] == -1:
                    atlantic[a[0]][a[1]] = 3
                    self.dfs(mat, a[0], a[1], whichOcean, atlantic, pacific)
            else:  # mark pacific ocean
                if mat[a[0]][a[1]] >= mat[r][c] and pacific[a[0]][a[1]] == -1:
                    pacific[a[0]][a[1]] = 2
                    self.dfs(mat, a[0], a[1], whichOcean, atlantic, pacific)

    def getAdjance(self, r, c, lengthR, lengthC):
        adj = []
        rs = []
        cs = []
        if r + 1 < lengthR:
            rs.append(r+1)
        if r - 1 >= 0:
            rs.append(r-1)
        if c + 1 < lengthC:
            cs.append(c+1)
        if c - 1 >= 0:
            cs.append(c-1)
        for c0 in cs:
            adj.append((r, c0))
        for r0 in rs:
            adj.append((r0, c))
        return adj
```
