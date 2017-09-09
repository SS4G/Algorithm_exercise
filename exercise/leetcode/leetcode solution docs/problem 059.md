## 59. Spiral Matrix II

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:

```
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

#### tips

这个没啥难度 按照螺旋的方式写出一个获取下一个坐标的迭代器即可
#### mycode
```
class Solution(object):
    def __init__(self):
        self.TO_RIGHT = 0
        self.TO_DOWN = 1
        self.TO_LEFT = 2
        self.TO_UP = 3
        self.nextDirection = [self.TO_DOWN, self.TO_LEFT, self.TO_UP, self.TO_RIGHT]  # rotated array

    def generateMatrix(self, n):
        """
        :type n: int
        :rtype: List[List[int]]
        """
        if n == 0:
            return []

        mark = []
        rL = n
        cL = n
        a = [False, ] * cL
        b = [0, ]*cL
        matrix = []
        for i in range(rL):
            mark.append(a[:])
            matrix.append(b[:])
        i = 0
        curDirection = self.TO_RIGHT
        r = 0
        c = 0

        while i < rL*cL:
            matrix[r][c] = i + 1
            mark[r][c] = True
            r, c, curDirection = self.getNext(r, c, curDirection, rL, cL, mark)
            i += 1
        return matrix

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
