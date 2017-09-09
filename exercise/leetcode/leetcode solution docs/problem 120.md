## 120. Triangle

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
- Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
- 


#### tips
典型的dp问题 从下到上 用一个数组 记录一排点到底部的最短路径 供上一排使用 

#### mycode

```
class Solution(object):
    def minimumTotal(self, triangle):
        """
        :type triangle: List[List[int]]
        :rtype: int
        """
        rL = len(triangle)
        lastRowRecord = [0, ]*rL
        row = rL - 1
        while row >= 0:
            if row == rL - 1:
                lastRowRecord = triangle[-1][:]
            else:
                oldLastRowRecord = lastRowRecord[:]
                for j in range(row + 1):
                    lastRowRecord[j] = triangle[row][j] + min(oldLastRowRecord[j], oldLastRowRecord[j+1])
            row -= 1
        return lastRowRecord[0]
```
