## 240. Search a 2D Matrix II Add to List

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:


```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

Given target = 5, return true.

Given target = 20, return false.
#### tips
可以使用二分查找
也可以使用从右上角开始搜索的办法O(m+N)
#### mycode
```Java
//binary Search O(m*lgn)
class Leet240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length <=0)
            return false;
        int xLength = matrix[0].length; //horizontal
        int yLength = matrix.length; //vertical
        int lo = 0;
        int hi = xLength-1;
        int mid = (lo + hi) >> 1;
        while (lo <= hi) { // search first line of matrix
            if (matrix[0][mid] == target)
                return true;
            else if (matrix[0][mid] > target) {
                hi = mid-1;
                mid = (lo + hi) >> 1;
            }
            else {
                lo = mid+1;
                mid = (lo + hi) >> 1;
            }
        }
        int horizontalIndexHi = hi;

        lo = 0;
        hi = xLength-1;
        mid = (lo + hi) >> 1;
        while (lo <= hi) { // search first line of matrix
            if (matrix[yLength-1][mid] == target)
                return true;
            else if (matrix[yLength-1][mid] > target) {
                hi = mid-1;
                mid = (lo + hi) >> 1;
            }
            else {
                lo = mid+1;
                mid = (lo + hi) >> 1;
            }
        }
        int horizontalIndexLo = lo;

        for (int hrIndex = horizontalIndexLo; hrIndex <= horizontalIndexHi; hrIndex++) {
            lo = 0;
            hi = yLength-1;
            mid = (lo + hi) >> 1;
            while (lo <= hi) { // search first line of matrix
                if (matrix[mid][hrIndex] == target)
                    return true;
                else if (matrix[mid][hrIndex] > target) {
                    hi = mid-1;
                    mid = (lo + hi) >> 1;
                }
                else if (matrix[mid][hrIndex] < target){
                    lo = mid+1;
                    mid = (lo + hi) >> 1;
                }
            }
        }
        return false;
    }
}
public class Leet240_t {
    public static void main(String[] args) {
        Leet240 s = new Leet240();
        int[][] mat = {
        {1,   4,  7, 11, 15},
        {2,   5,  8, 12, 19},
        {3,   6,  9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}
    };
        System.out.println(s.searchMatrix(mat, 30));
        System.out.println(s.searchMatrix(mat, 16));
        System.out.println(s.searchMatrix(mat, 1));
        System.out.println(s.searchMatrix(mat, 5));
        System.out.println(s.searchMatrix(mat, 18));
        System.out.println(s.searchMatrix(mat, 22));
    }
}
```

#### others code

```Java
//O(m+n)
//start with right top
public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
```
