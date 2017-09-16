package AlgorithmTraining.exercise.toOffer;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/9/14.
 */

class Solution063 {
    public int movingCount(int threshold, int rows, int cols)
    {
        if (rows == 0 || cols == 0)
            return 0;

        boolean[][] marked = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                marked[r][c] = false;
            }
        }
        int[] cnt = new int[1];
        dfsHelper(marked, cnt, 0, 0, threshold);
        return cnt[0];
    }

    private void dfsHelper(boolean[][] marked, int[] cnt, int r, int c, int thresold) {
        int rLength = marked.length;
        int cLength = marked[0].length;
        if (!marked[r][c] && validPos(r, c, thresold)) {
            marked[r][c] = true;
            System.out.println(r + ":" + c);
            cnt[0] += 1;
            List<List<Integer>> adjs = getAdjance(r, c, rLength, cLength);
            for (List<Integer> pos : adjs) {
                int tmpr = pos.get(0);
                int tmpc = pos.get(1);
                dfsHelper(marked, cnt, tmpr, tmpc, thresold);
            }
        }
    }

    boolean validPos(int r, int c, int thresold) {
        String s = Integer.toString(r) + Integer.toString(c);
        int sum = 0;
        for (Character ch : s.toCharArray()) {
            sum += (ch - '0');
        }
        return  sum <= thresold;
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

public class No063 {
    public static void main(String[] args) {
        Solution063 s = new Solution063();
        //System.out.println(s.movingCount(5, 10, 10));
        System.out.println(s.movingCount(10, 1, 100));
        //s.validPos(0, 90, 100);
    }
}
