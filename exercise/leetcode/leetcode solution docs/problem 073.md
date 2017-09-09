## 73. Set Matrix Zeroes
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

#### tips
就是把用过的行和列用集合记录下来即可
但是这个方法不是常数空间的

要想使用常数空间的解放 应该首先在第一行或者第一列上做标记 然后 用两个变量记录第一行或者第一列上是否有点 来确定标记是本来就应该有的还是只是别的行或者列上标记上的

#### mycode
```
class Solution(object):
    def setZeroes(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """
        rL = len(matrix)
        if rL == 0:
            return
        cL = len(matrix[0])
        rSet = set([])
        cSet = set([])
        for rx in range(rL):
            for cx in range(cL):
                if matrix[rx][cx] == 0:
                    rSet.add(rx)
                    cSet.add(cx)

        for i in rSet:
            for j in range(cL):
                matrix[i][j] = 0

        for j in cSet:
            for i in range(rL):
                matrix[i][j] = 0

        return

if __name__ == "__main__":
    s = Solution()
    mat = [
        [1, 2, 0],
        [1, 0, 0],
        [1, 2, 3]
    ]
    # [[1, 2, 0],[1, 0, 0],[1, 2, 3]]
    s.setZeroes(mat)
    for li in mat:
        print(li)
```

#### O（1） spacecode

```
public class Solution {
public void setZeroes(int[][] matrix) {
    boolean fr = false,fc = false;
    for(int i = 0; i < matrix.length; i++) {
        for(int j = 0; j < matrix[0].length; j++) {
            if(matrix[i][j] == 0) {
                if(i == 0) fr = true;
                if(j == 0) fc = true;
                matrix[0][j] = 0;
                matrix[i][0] = 0;
            }
        }
    }
    for(int i = 1; i < matrix.length; i++) {
        for(int j = 1; j < matrix[0].length; j++) {
            if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    if(fr) {
        for(int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = 0;
        }
    }
    if(fc) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
    }
    
}
```
