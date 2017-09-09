package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by g55 on 17-5-6.
 */
public class Leet566 {
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int ori_r = nums.length;
        int ori_c = nums[0].length;
        int oriSize = ori_c*ori_r;
        if (oriSize != r*c)
            return nums;
        int[][] newMat = new int[r][c];
        int newIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                newMat[newIndex/c][newIndex%c] = nums[i][j];
                newIndex++;
            }
        }
        return newMat;
    }
    public static void main(String[] args) {
        int[][] mat0 = {{1,2},{3,4}};
        int[][] reshapedMat0 = matrixReshape(mat0, 1, 4);
        for (int i = 0; i < reshapedMat0.length; i++)
            for (int j = 0; j < reshapedMat0[0].length; j++)
                System.out.println(reshapedMat0[i][j]);
    }
}
