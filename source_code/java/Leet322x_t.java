package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/14.
 */
import java.util.*;
class Leet322x {
    public int coinChange(int[] coins, int amount) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int fewest = getfewest(map, amount, coins);
        return fewest == Integer.MAX_VALUE - 1 ? -1 : fewest;
    }

    int getfewest(HashMap<Integer, Integer> rec, int amount, int[] coins) {
        if (amount < 0)
            return Integer.MAX_VALUE - 1;
        if (amount == 0)
            return 0;
        if (rec.containsKey(amount))
            return rec.get(amount);
        else {
            int fewestAmount = Integer.MAX_VALUE;
            for (int i : coins) {
                fewestAmount = Math.min(getfewest(rec, amount - i, coins) + 1, fewestAmount);
            }
            if (fewestAmount == Integer.MAX_VALUE)
                fewestAmount = Integer.MAX_VALUE - 1;
            rec.put(amount, fewestAmount);
            return fewestAmount;
        }
    }
}

public class Leet322x_t {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        Leet322x leet = new Leet322x();
        System.out.println(leet.coinChange(coins, 11));
        coins = new int[]{2, 4, 6, 8};
        System.out.println(leet.coinChange(coins, 15));
    }
}
