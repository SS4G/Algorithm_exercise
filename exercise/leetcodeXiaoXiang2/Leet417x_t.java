package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
import AlgorithmTraining.G55Utils.Java.ArrayUtil;

import java.util.*;
class Leet417x {
    private final int PAC = 0x01;
    private final int ATL = 0x02;
    private final int NON = 0x00;
    public List<int[]> pacificAtlantic(int[][] matrix) {
        final int YL = matrix.length;
        if (YL == 0)
            return new ArrayList<>();
        final int XL = matrix[0].length;
        if (XL == 0)
            return new ArrayList<>();
        int[][] matrixMark = new int[matrix.length][matrix[0].length];


        for (int x = 0; x < XL; x++) { // build mark
            for (int y = 0; y < YL; y++) {
                matrixMark[y][x] = NON;
            }
        }

        for (int x = 0; x < XL; x++) {
            bfsHelper(matrix, matrixMark, x, 0, XL, YL, PAC);
            bfsHelper(matrix, matrixMark, x, YL - 1, XL, YL, ATL);
        }

        for (int y = 0; y < YL; y++) {
            bfsHelper(matrix, matrixMark, 0, y, XL, YL, PAC);
            bfsHelper(matrix, matrixMark, XL - 1, y, XL, YL, ATL);
        }


        List<int[]> result = new ArrayList<>();
        for (int x = 0; x < XL; x++) {
            for (int y = 0; y < YL; y++) {
                if ((matrixMark[y][x] & (PAC | ATL)) == (PAC | ATL)) {
                    result.add(new int[]{y, x});
                }
            }
        }
        return result;
    }

    public void bfsHelper(int[][] matrix, int[][] matMark, int x, int y, int XL, int YL, int markVal) {
        if ((matMark[y][x] & markVal) == 0) {
            matMark[y][x] |= markVal; // mark first node
            ArrayList<int[]> fifo = new ArrayList<>();
            int rd = 0;
            fifo.add(new int[]{x, y});
            while (rd < fifo.size()) {
                int xPos = fifo.get(rd)[0];
                int yPos = fifo.get(rd)[1];
                List<int[]> adjs = getAdj(XL, YL, xPos, yPos);
                for (int[] adjPoint : adjs) {
                    int adjx = adjPoint[0];
                    int adjy = adjPoint[1];
                    if (matrix[adjy][adjx] >= matrix[yPos][xPos] && (matMark[adjy][adjx] & markVal) == 0) { // just add unmarked

                        fifo.add(adjPoint);
                        matMark[adjy][adjx] |= markVal;
                    }
                }
                rd++;
            }
        }
    }

    private List<int[]> getAdj(int xL, int yL, int x, int y) {
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        if (x > 0)
            xs.add(x - 1);
        if (y > 0)
            ys.add(y - 1);
        if (x < xL - 1)
            xs.add(x + 1);
        if (y < yL - 1)
            ys.add(y + 1);
        List<int[]> adjs = new ArrayList<>();
        for (int x0 : xs) {
            int[] xx = new int[2];
            xx[0] = x0;
            xx[1] = y;
            adjs.add(xx);
        }

        for (int y0 : ys) {
            int[] yy = new int[2];
            yy[0] = x;
            yy[1] = y0;
            adjs.add(yy);
        }
        return adjs;
    }
}

public class Leet417x_t {
    public static void main(String[] args) {
        int[][] map = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4},
        };
        map = new int[][] {{1,3,3,1,3,2},{3,2,1,3,2,3},{3,3,3,2,3,1}};
        Leet417x leet = new Leet417x();
        List<int[]> result = leet.pacificAtlantic(map);
        for (int[] res : result) {
            System.out.println(res[0] + ":" + res[1]);
        }

    }
}
