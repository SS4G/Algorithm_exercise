package AlgorithmTraining.exercise.toOffer;

/**
 * Created by szh-920 on 17-9-9.
 */
import java.util.*;
class Solution020 {
    private final int UP = 0;
    private final int DOWN = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int rowLength = matrix.length;
        if (rowLength <= 0) {
            return new ArrayList<Integer>();
        }
        else {
            int colLength = matrix[0].length;
            boolean[][] checked = new boolean[rowLength][colLength];
            for (int r = 0; r < rowLength; r++) {
                for (int c = 0; c < colLength; c++) {
                    checked[r][c] = false;
                }
            }
            int row = 0;
            int col = 0;
            int dir = RIGHT;
            int[] nextDir = {row, col, dir};
            ArrayList<Integer> result = new ArrayList<>();
            do {
                row = nextDir[0];
                col = nextDir[1];
                dir = nextDir[2];
                result.add(matrix[row][col]);
                checked[row][col] = true;
                nextDir = next(row, col, dir, rowLength, colLength, checked);
            } while (nextDir != null);
            return result;
        }
    }

    private int[] next(int r, int c, int direction, int rowLength, int colLength, boolean[][] checked) {
        switch (direction) {
            case UP:
                if (r == 0 || checked[r - 1][c]) {
                    if (c + 1 < colLength && !checked[r][c + 1]) {
                        return new int[]{r, c + 1, RIGHT};
                    }
                    else
                        return null;
                }
                else {
                    return new int[]{r - 1, c, UP};
                }
                //break;
            case DOWN:
                if (r == rowLength - 1 || checked[r + 1][c]) {
                    if (c - 1 >= 0 && !checked[r][c - 1]) {
                        return new int[]{r, c - 1, LEFT};
                    }
                    else
                        return null;
                }
                else {
                    return new int[]{r + 1, c, DOWN};
                }
                //break;
            case LEFT:
                if (c == 0 || checked[r][c - 1]) {
                    if (r - 1 >= 0 && !checked[r - 1][c]) {
                        return new int[]{r - 1, c, UP};
                    }
                    else
                        return null;
                }
                else {
                    return new int[]{r, c - 1, LEFT};
                }
                //break;
            case RIGHT:
                if (c == colLength - 1 || checked[r][c + 1]) {
                    if (r + 1 < rowLength && !checked[r + 1][c]) {
                        return new int[]{r + 1, c, DOWN};
                    }
                    else
                        return null;
                }
                else {
                    return new int[]{r, c + 1, RIGHT};
                }
                //break;
            default: return null;
        }
    }
}

public class No020 {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        mat = new int[][]{};
        mat = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };
        mat = new int[][]{
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
        };
        Solution020 s = new Solution020();
        System.out.println(s.printMatrix(mat));
    }
}
