package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/14.
 */
class Leet174 {
    public int calculateMinimumHP(int[][] dungeon) {
        /*
        if (dungeon.length == 0 || dungeon[0].length == 0) {

        }
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = dungeon[i][j];
                }
                else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + dungeon[i][j];
                }
                else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + dungeon[i][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + dungeon[i][j];
                }
            }
        }*/
        return 0;
    }
}

public class Leet174x_t {
    public static void main(String[] args) {
    }
}
