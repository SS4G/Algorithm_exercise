package AlgorithmTraining.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/10.
 */
import java.util.*;
public class Leet051 {
    public static List<List<String>> solveNQueens(int n) {
        List<int[]> resArr = new ArrayList<int[]>(1024);
        final int dim = n;
        int[] tmpMat = new int[dim];
        int this_r = 0;
        int this_c = 0;
        int alreadyPlacedAmount = 0;
        TreeSet<Integer> useless_r = new TreeSet<Integer>();//不可放置的行
        TreeSet<Integer> useless_c = new TreeSet<Integer>();//不可放置的列
        TreeSet<Integer> useless_l = new TreeSet<Integer>();//不可放置的斜线

        Extern_Loop:
        while (true) {
            // assert this_c < dim && this_r < dim
            System.out.println("c = "+this_c+" r = "+this_r);
            System.out.println("setSize = "+useless_c.size());
            tmpMat[this_c] = this_r;
            //System.out.println("c = "+this_c+" r = "+this_r);
            //以下只修改下次要写入的内容 而不修改tmpMat结果
            if (conflict(this_r, this_c, useless_r, useless_c, useless_l)) {
                this_r++;
                //System.out.println("non");

                if (this_r == dim) {
                    while (this_r >= dim) {
                        this_c--;
                        if (this_c < 0)
                            break Extern_Loop;
                        else
                            deleteFromRecord(tmpMat[this_c], this_c, useless_r, useless_c, useless_l);
                        this_r = tmpMat[this_c] + 1;
                    }
                }
            } else {
                //System.out.println("yes");
                if (this_c == dim-1) {
                    int[] tmp = new int[dim];
                    copyArray(tmp, tmpMat);
                    resArr.add(tmp);//add result
                    //this_c = dim - 1;
                    this_r++;
                    if (this_r == dim) {
                        while (this_r >= dim) {
                            this_c--;
                            if (this_c < 0)
                                break Extern_Loop;
                            else
                                deleteFromRecord(tmpMat[this_c], this_c, useless_r, useless_c, useless_l);
                            this_r = tmpMat[this_c] + 1;
                        }
                    }
                }
                else {
                    addToRecord(this_r, this_c, useless_r, useless_c, useless_l);
                    this_c++;
                    this_r = 0;
                }
            }
        }
        /*for (int[] a : resArr) {
            for (int j : a) {
                System.out.print(j+",");
            }
            System.out.println("");
        }*/
        return plotResult(resArr);
    }

    private static void addToRecord(int r, int c, TreeSet<Integer> useless_r,
                        TreeSet<Integer> useless_c, TreeSet<Integer> useless_l) {
        useless_r.add(r);
        useless_c.add(c);
        useless_l.add(r-c); //save r-c
        useless_l.add(r+c);
    }

    private static void deleteFromRecord(int r, int c, TreeSet<Integer> useless_r,
                        TreeSet<Integer> useless_c, TreeSet<Integer> useless_l) {
        useless_r.remove(r);
        useless_c.remove(c);
        useless_l.remove(r-c); //save r-c
        useless_l.remove(r+c);
    }

    private static boolean conflict(int this_r, int this_c, TreeSet<Integer> useless_r,
                                    TreeSet<Integer> useless_c,TreeSet<Integer> useless_l) {
        return useless_r.contains(this_r) || useless_c.contains(this_c)
                || useless_l.contains(this_r - this_c) || useless_l.contains(this_c + this_r);
    }

    private static void copyArray(int[] dst, final int[] src) {
        for (int i = 0; i < dst.length; i++) {
            dst[i] = src[i];
        }
    }

    public static List<List<String>> plotResult(List<int[]> resArr) {
        // resArr[r] = c point at r row c column
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
            //mat = mat0;

            oneMat = new ArrayList<String>(dim);
            for (int r = 0; r < dim; r++) {
                //System.out.println(mat[r]);
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
