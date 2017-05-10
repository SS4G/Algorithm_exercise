package AlgorithmTraining.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/10.
 */
import java.util.*;
public class Leet051 {
    public static List<List<String>> solveNQueens(int n) {
        return null;
    }
    public static List<List<String>> plotResult(int[][] resArr) {
        // resArr[r] = c point at r row c column
        int dim=0;

        List<List<String>> result = new LinkedList<List<String>>();

        String row = null;

        dim = resArr[0].length;
        List<String> oneMat = new ArrayList<String>(dim);
        char[] oneRow = new char[dim];
        for (int[] mat : resArr) {
            for (int r = 0; r < dim; r++) {
                System.out.println(mat[r]);
                for (int c = 0; c < dim; c++) {
                    if (c == mat[r])
                        oneRow[c] = 'Q';
                    else
                        oneRow[c] = '.';
                }
                row = new String(oneRow);
                oneMat.set(r, row);
            }
            result.add(oneMat);
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] arr = {
            {0, 1, 2, 3},
            {3, 2, 1, 0},
            {1, 2, 2, 3}
        };
        List<List<String>>res = plotResult(arr);
        for (List<String> mat : res) {
            for (String row : mat) {
                System.out.println(row);
            }
            System.out.println("------");
        }
    }
}
