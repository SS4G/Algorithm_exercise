package AlgorithmTraining.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/6/29.
 */
class Leet516 {
    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0)
            return 0;
        int[][] dpRec = new int[s.length() + 1][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dpRec[i][i] = 1;
        }
        for (int i0 = s.length()-1; i0 >=0; i0--) {
            for (int j = i0 + 1; j < s.length(); j++) {
                if (s.charAt(i0) == s.charAt(j))
                    dpRec[i0][j] = dpRec[i0 + 1][j - 1] + 2;
                else
                    dpRec[i0][j] = Math.max(dpRec[i0 + 1][j], dpRec[i0][j - 1]);
            }
        }
        return dpRec[0][s.length() - 1];
    }
}
public class Leet516_t {
    public static void main(String[] args) {
        String s = "aabaa";
        Leet516 l = new Leet516();
        System.out.println(l.longestPalindromeSubseq(s));
    }
}
