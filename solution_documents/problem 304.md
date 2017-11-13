##  304. Range Sum Query 2D - Immutable

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
![image](https://leetcode.com/static/images/courses/range_sum_query_2d.png)
Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

```
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]
```

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.

#### tips
这个题目因为要查询多次且 矩阵本身是不变的所以 可以吧 (0,0)->(i,j)的结果算出来存放到一个矩阵中 矩阵中存储的值都是 左上角为0，0 右下角为ij的长方形的值 画个图就明白了
任何矩阵的值都可以 用row1，col1 -> row2, col2 下面的表达式算出 
```
res = self.getSumRecord(row2, col2) - self.getSumRecord(row2, col1 - 1) - self.getSumRecord(row1 - 1, col2)\
                + self.getSumRecord(row1 - 1, col1 - 1)
```


#### mycode

```
class NumMatrix(object):
    def __init__(self, matrix):
        """
        :type matrix: List[List[int]]
        """
        self.mat = matrix
        self.rL = len(matrix)
        if self.rL > 0:
            self.cL = len(matrix[0])
        else:
            self.cL = -1
        self.isEmpty = False
        if self.cL > 0 and self.rL > 0:
            self.resRecord = []
            for i in range(self.rL):
                self.resRecord.append([None, ]*self.cL)

            for i in range(self.rL):
                for j in range(self.cL):
                    if j != 0:
                        self.resRecord[i][j] = self.resRecord[i][j-1] + sum([self.mat[k][j] for k in range(i + 1)])
                    else:
                        if i == 0:
                            self.resRecord[0][0] = self.mat[0][0]
                        else:
                            self.resRecord[i][0] = self.resRecord[i - 1][0] + self.mat[i][0]
        else:
            self.isEmpty = True

    def sumRegion(self, row1, col1, row2, col2):
        """
        :type row1: int
        :type col1: int
        :type row2: int
        :type col2: int
        :rtype: int
        """
        if self.isEmpty:
            return 0
        else:
            res = self.getSumRecord(row2, col2) - self.getSumRecord(row2, col1 - 1) - self.getSumRecord(row1 - 1, col2)\
                + self.getSumRecord(row1 - 1, col1 - 1)
        return res

    def getSumRecord(self, r, c):
        if r < 0 or c < 0:
            return 0
        return self.resRecord[r][c]
```
