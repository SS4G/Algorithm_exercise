package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/13.
 */
import java.util.*;
class Solution061 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if (str.length <= 0)
            return false;
        if (rows <= 0 || cols <= 0)
            return false;

        char[][] mat = new char[rows][cols];
        boolean[][] matChecked = new boolean[rows][cols];

        int curRow = 0;
        int curCol = 0;
        for (int idx = 0; idx < matrix.length; idx++) {
            mat[curRow][curCol] = matrix[idx];
            curCol++;
            if (curCol >= cols) {
                curRow++;
                curCol = 0;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matChecked[i][j] = false;
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfsSerach(mat, matChecked, r, c, str, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsSerach(char[][] mat, boolean[][] matChecked, int r, int c, char[] str, int serachStep) {
        int rowLength = mat.length;
        int colLength = mat[0].length;
        //System.out.println(serachStep);
        if (mat[r][c] == str[serachStep] && (!matChecked[r][c])) {
            if (serachStep == str.length - 1)
                return true;
            matChecked[r][c] = true;
            List<List<Integer>> adjs = getAdjance(r, c, rowLength, colLength);
            //System.out.println(adjs);
            boolean res = false;
            for (List<Integer> pos : adjs) {
                res |= dfsSerach(mat, matChecked, pos.get(0), pos.get(1), str, serachStep + 1);
            }
            matChecked[r][c] = false;
            return res;
        }
        else {
            return false;
        }
    }

    private List<List<Integer>> getAdjance(int curRow, int curCol, int row, int col) {
        List<List<Integer>> poses = new ArrayList<>();
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        if (curRow > 0) {
            rows.add(curRow - 1);
        }
        if (curRow < row - 1) {
            rows.add(curRow + 1);
        }
        if (curCol > 0) {
            cols.add(curCol - 1);
        }
        if (curCol < col - 1) {
            cols.add(curCol + 1);
        }
        for (int r : rows) {
            List<Integer> onePos = new ArrayList<>();
            onePos.add(r);
            onePos.add(curCol);
            poses.add(onePos);
        }
        for (int c : cols) {
            List<Integer> onePos = new ArrayList<>();
            onePos.add(curRow);
            onePos.add(c);
            poses.add(onePos);
        }
        return poses;
    }
}
public class No061 {
    public static void main(String[] args) {
        char[] mat = "abcdefghijklmnop".toCharArray();
        char[] str = "fgkop".toCharArray(); //true
        Solution061 s = new Solution061();
        assert s.hasPath(mat, 4, 4, "fgkop".toCharArray());
        assert s.hasPath(mat, 4, 4, "bfjim".toCharArray());
        assert s.hasPath(mat, 4, 4, "efghlp".toCharArray());
        assert !s.hasPath(mat, 4, 4, "abcdhlponjkl".toCharArray());
        assert !s.hasPath(mat, 4, 4, "abcabckl".toCharArray());
    }
}
