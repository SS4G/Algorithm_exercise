## 498. Diagonal Traverse

Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:

```
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
```
![img](https://leetcode.com/static/images/problemset/diagonal_traverse.png)

```
Output:  [1,2,4,7,5,3,6,8,9]
```



Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.
#### tips
写一个next函数来根据当前的点的坐标来获取后续点的坐标，直到迭代到 右下角的点终止
需要注意的点是 右上角和左下角， 这两个个地方的点容易 搞错方向 ，需要注意的点是getNext中的if条件是有先后的 不能顺序出错
#### mycode
```Python
class Solution(object):
    def findDiagonalOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        r = 0
        c = 0
        dir = True
        if len(matrix) == 0:
            return []
        res = []
        res.append(matrix[0][0])
        while True:
            r, c, dir = self.getNext(r, c, dir, len(matrix), len(matrix[0]))
            if r != -1:
                res.append(matrix[r][c])
            else:
                break
        return res

    def getNext(self, r, c, dir, lenR, lenC):
        if r == lenR - 1 and c == lenC - 1:
            return -1, -1, True

        if dir is True:  # up right
            if r > 0 and c < lenC - 1:  # up right direction at middle pos
                return r - 1, c + 1, True
            elif r >= 0 and c == lenC - 1:  # up right direction at right side or up right conner
                return r + 1, lenC - 1, False
            elif r == 0 and c < lenC - 1:  # up right direction at up side
                return 0, c + 1, False
            else:
                assert False, "logic error"
        else:  # down Left
            if r < lenR - 1 and c > 0:  # down left direction at middle pos
                return r + 1, c - 1, False
            elif r == lenR - 1 and c < lenC - 1:  # down left direction at down side or left down conner
                return lenR - 1, c + 1, True
            elif r < lenR - 1 and c == 0:  # down left direction at left side
                return r + 1, 0, True
            else:
                assert False, "logic error"
```
