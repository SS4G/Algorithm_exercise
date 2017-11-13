## 661. Image Smoother 
Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
```
[[1,1,1],
 [1,0,1],
 [1,1,1]]
```

Output:
```
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
```

Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
- The value in the given matrix is in the range of [0, 255].
- The length and width of the given matrix are in the range of [1, 150].
- 


#### tips
没啥好说的 就是按照迭代器 与笛卡尔积的思想去产生周围的点 然后就可以了求个平均值

#### mycode

```
class Solution(object):
    def imageSmoother(self, M):
        """
        :type M: List[List[int]]
        :rtype: List[List[int]]
        """
        rLength = len(M)
        if rLength <= 0:
            return []
        cLength = len(M[0])

        res = [[0, ] * cLength for i in range(rLength)]
        for r in range(rLength):
            for c in range(cLength):
                res[r][c] = self.getAdj(M, r, c, rLength, cLength)
        return res

    def getAdj(self, M, r, c, rLength, cLength):
        rs = [r, ]
        if r > 0:
            rs.append(r - 1)
        if r < rLength - 1:
            rs.append(r + 1)

        cs = [c, ]
        if c > 0:
            cs.append(c - 1)
        if c < cLength - 1:
            cs.append(c + 1)
        res = []
        i = 0
        for r0 in rs:
            for c0 in cs:
                res.append(M[r0][c0])
                i += 1
        #print(rs, cs)
        #print(res)
        return sum(res) // i
```
