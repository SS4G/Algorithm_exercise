package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/18.
 */
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
