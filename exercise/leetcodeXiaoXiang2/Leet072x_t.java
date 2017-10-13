package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/12.
 */
class Leet072x {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i != 0 && j != 0) {
                    dp[i][j] = -1;
                }
                else {
                    if (i == 0)
                        dp[i][j] = j;
                    else
                        dp[i][j] = i;
                }
            }
        }
        int res = getDp(word1, word2, dp, m, n);
        return res;
    }

    public int getDp(String w1, String w2, int[][] dp, int m, int n) {
        //System.out.println("m=" + m + " n=" + n);
        if (dp[m][n] == -1) {
            dp[m][n] = Math.min(getDp(w1, w2, dp, m - 1, n) + 1,
                    Math.min(getDp(w1, w2, dp, m, n - 1) + 1, getDp(w1, w2, dp, m - 1, n - 1) + (w1.charAt(m - 1) == w2.charAt(n - 1) ? 0 : 1)));
        }
        //System.out.println("dp[" + m + "][" + n + "]=" + dp[m][n]);
        return dp[m][n];
    }
}

public class Leet072x_t {
    public static void main(String[] args) {
        Leet072x leet = new Leet072x();
        System.out.println(leet.minDistance("abcdh", "abfffdh"));
    }
}
