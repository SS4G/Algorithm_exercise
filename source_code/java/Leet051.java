package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/10.
 */
import java.util.*;
public class Leet051 {
    private static final int ROW = 0;
	private static final int COLUMN = 1;
	private static final int POS_LINE = 2;
	private static final int NEG_LINE = 3;
    public static List<List<String>> solveNQueens(int n) {
        List<int[]> resArr = new ArrayList<int[]>(1024);
        final int dim = n;
        int[] tmpMat = new int[dim];
        int this_r = 0;
        int this_c = 0;
        int alreadyPlacedAmount = 0;

     	TreeSet<Integer> uselessROW = new TreeSet<Integer>();
     	TreeSet<Integer> uselessCOL = new TreeSet<Integer>();
     	TreeSet<Integer> uselessPOS = new TreeSet<Integer>();
     	TreeSet<Integer> uselessNEG = new TreeSet<Integer>();

        Extern_Loop:
        while (true) {
            tmpMat[this_c] = this_r;
            if (conflict(this_r, this_c, uselessROW, uselessCOL, uselessPOS, uselessNEG)) {
                this_r++;
                if (this_r == dim) {
                    while (this_r >= dim) {
                        this_c--;
                        if (this_c < 0)
                            break Extern_Loop;
                        else
                            deleteFromRecord(tmpMat[this_c], this_c, uselessROW, uselessCOL, uselessPOS, uselessNEG);
                        this_r = tmpMat[this_c] + 1;
                    }
                }
            } else {
                //System.out.println("yes");
                if (this_c == dim-1) {
                    int[] tmp = new int[dim];
                    copyArray(tmp, tmpMat);
                    resArr.add(tmp);//add result
                    this_r++;
                    if (this_r == dim) {
                        while (this_r >= dim) {
                            this_c--;
                            if (this_c < 0)
                                break Extern_Loop;
                            else
                                deleteFromRecord(tmpMat[this_c], this_c, uselessROW, uselessCOL, uselessPOS, uselessNEG);
                            this_r = tmpMat[this_c] + 1;
                        }
                    }
                }
                else {
                    addToRecord(this_r, this_c, uselessROW, uselessCOL, uselessPOS, uselessNEG);
                    this_c++;
                    this_r = 0;
                }
            }
        }
        return plotResult(resArr);
    }

    private static void addToRecord(int r, int c, TreeSet<Integer> uselessROW, TreeSet<Integer> uselessCOL,
                                    TreeSet<Integer> uselessPOS, TreeSet<Integer> uselessNEG) {
        uselessROW.add(r);
        uselessCOL.add(c);
        uselessPOS.add(r+c);
        uselessNEG.add(r-c);
    }

    private static void deleteFromRecord(int r, int c, TreeSet<Integer> uselessROW, TreeSet<Integer> uselessCOL,
                                    TreeSet<Integer> uselessPOS, TreeSet<Integer> uselessNEG) {
        uselessROW.remove(r);
        uselessCOL.remove(c);
        uselessPOS.remove(r+c);
        uselessNEG.remove(r-c);
    }

    private static boolean conflict(int r, int c,  TreeSet<Integer> uselessROW, TreeSet<Integer> uselessCOL,
                                    TreeSet<Integer> uselessPOS, TreeSet<Integer> uselessNEG) {
        return  uselessROW.contains(r) ||
                uselessCOL.contains(c) ||
                uselessPOS.contains(r+c) ||
                uselessNEG.contains(r-c);
    }

    private static void copyArray(int[] dst, final int[] src) {
        for (int i = 0; i < dst.length; i++) {
            dst[i] = src[i];
        }
    }

    public static List<List<String>> plotResult(List<int[]> resArr) {
        int dim=0;

        List<List<String>> result = new LinkedList<List<String>>();

        String row = null;
        if (resArr.size() < 1)
            return result;
        dim = resArr.get(0).length;
        List<String> oneMat = null;
        char[] oneRow = new char[dim];

        for (int[] mat0 : resArr) {
            int[] mat = new int[dim];

            for (int j = 0; j < dim; j++) //rotate the matrix
                mat[mat0[j]] = j;

            oneMat = new ArrayList<String>(dim);
            for (int r = 0; r < dim; r++) {
                for (int c = 0; c < dim; c++) {
                    if (c == mat[r])
                        oneRow[c] = 'Q';
                    else
                        oneRow[c] = '.';
                }
                row = new String(oneRow);
                oneMat.add(row);
            }
            result.add(oneMat);
        }
        return result;
    }

    public static void main(String[] args) {

        List<List<String>>res = solveNQueens(4);

        for (List<String> mat : res) {
            for (String row : mat) {
                System.out.println(row);
            }
            System.out.println("------");
        }
    }
}
