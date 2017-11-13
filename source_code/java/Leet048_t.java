package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/16.
 */
class Leet048 {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        int up = 0, left=0, dim = N;
        int half = N>>1;
        for (int layer = 0; layer < half; layer++) {
            rotateCircle(matrix, left, up, dim);
            up++;
            left++;
            dim-=2;
        }
    }
    private void rotateCircle(int[][] mat, int left, int up, int dim) {
        int tmpSpace = 0;
        for (int x = 0; x < dim-1; x++) {
            //System.out.println(halfDim);
            tmpSpace = mat[up][left+x];
            mat[up][left+x] = mat[up+dim-1-x][left];
            mat[up+dim-1-x][left] = mat[up+dim-1][left+dim-1-x];
            mat[up+dim-1][left+dim-1-x] = mat[up+x][left+dim-1];
            mat[up+x][left+dim-1] = tmpSpace;
        }
    }
}
public class Leet048_t {
    public static void main(String[] args) {
        int[][] mat = {{1,2},{3,4}};
        int[][] mat1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] mat2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        showMat(mat2);
        Leet048 s = new Leet048();
        s.rotate(mat2);
        showMat(mat2);
    }
    public static void showMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%2d ",mat[i][j]);
            }
            System.out.println("");
        }
        System.out.println("------------------");
    }
}
