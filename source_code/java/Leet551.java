package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by g55 on 17-5-6.
 */
public class Leet551 {
    public static boolean checkRecord(String s) {
        char[] sArr = s.toCharArray();
        int[] a = {0, 0};
        int lMax = 0;
        for (char c : sArr) {
            if (c == 'L') {
                a[0]++;
            }
            else if (c == 'A') {
                a[1]++;
                lMax = lMax > a[0]? lMax : a[0];
                a[0] = 0;
            }
            else {
                lMax = lMax > a[0]? lMax : a[0];
                a[0] = 0;
            }
        }
        lMax = lMax > a[0]? lMax : a[0];
        //System.out.println(lMax);
        return lMax <= 2 && a[1] <= 1;
    }
    public static void main(String[] args) {
        //System.out.println(checkRecord("PPPLLAALAAL"));
        //System.out.println(checkRecord("LALL"));
        //System.out.println(checkRecord("LLLALL"));
        System.out.println(checkRecord("LLLL"));
    }
}
