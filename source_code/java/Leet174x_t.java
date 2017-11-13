package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.ArrayUtil;

/**
 * Created by G5501 on 2017/10/14.
 */
class Leet174x {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for (int i = dungeon.length - 1; i >= 0; i--) {
            for (int j = dungeon[0].length - 1; j >= 0; j--) {
                if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
                    dp[i][j] = dungeon[i][j] >= 0 ? 0 : -dungeon[i][j];
                } else if (i == dungeon.length - 1) {
                    //dp[i][j] = dp[i][j + 1] + dungeon[i][j] >= 0 ? 0 : -dungeon[i][j];
                    int tmp = dp[i][j + 1] - dungeon[i][j];
                    dp[i][j] = tmp > 0 ? tmp : 0;
                } else if (j == dungeon[0].length - 1) {
                    int tmp = dp[i + 1][j] - dungeon[i][j];
                    dp[i][j] = tmp > 0 ? tmp : 0;
                } else {
                    int tmp = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                    dp[i][j] = tmp > 0 ? tmp : 0;
                }
            }
        }
        //ArrayUtil.showArr2D(dp);
        return dp[0][0] + 1;
    }
}

public class Leet174x_t {
    public static void main(String[] args) {
        int[][] dun = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5},
        };
        Leet174x leet = new Leet174x();
        assert leet.calculateMinimumHP(dun) == 7;
    }
}
