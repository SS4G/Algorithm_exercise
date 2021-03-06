## 54. Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:


```
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
```

You should return [1,2,3,6,9,8,7,4,5].

#### tips
用简单的迭代器 根据当前的位置和方向算出下一个点的位置和方向
#### mycode
```
class Solution(object):
    def __init__(self):
        self.TO_RIGHT = 0
        self.TO_DOWN = 1
        self.TO_LEFT = 2
        self.TO_UP = 3
        self.nextDirection = [self.TO_DOWN, self.TO_LEFT, self.TO_UP, self.TO_RIGHT]  # rotated array

    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        mark = []
        rL = len(matrix)
        if rL == 0:
            return []
        cL = len(matrix[0])
        a = [False, ] * cL
        for i in range(rL):
            mark.append(a[:])
        i = 0
        curDirection = self.TO_RIGHT
        r = 0
        c = 0
        res = []
        while i < rL*cL:
            res.append(matrix[r][c])
            mark[r][c] = True
            r, c, curDirection = self.getNext(r, c, curDirection, rL, cL, mark)
            i += 1
        return res

    def getNext(self, r, c, curDir, rL, cL, mark):
        if curDir == self.TO_RIGHT:
            if c + 1 >= cL or mark[r][c + 1]:
                curDir = self.nextDirection[self.TO_RIGHT]
                return r + 1, c, curDir
            else:
                return r, c + 1, curDir
        elif curDir == self.TO_DOWN:
            if r + 1 >= rL or mark[r + 1][c]:
                curDir = self.nextDirection[self.TO_DOWN]
                return r, c - 1, curDir
            else:
                return r + 1, c, curDir
        elif curDir == self.TO_LEFT:
            if c - 1 < 0 or mark[r][c - 1]:
                curDir = self.nextDirection[self.TO_LEFT]
                return r - 1, c, curDir
            else:
                return r, c - 1, curDir
        elif curDir == self.TO_UP:
            if r - 1 < 0 or mark[r - 1][c]:
                curDir = self.nextDirection[self.TO_UP]
                return r, c + 1, curDir
            else:
                return r - 1, c, curDir
        else:
            assert False, "invalid args"
```
