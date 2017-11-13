package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.List;

/**
 * Created by G5501 on 2017/10/15.
 *
 */

class Leet260x {
    public int[] singleNumber(int[] nums) {
        int diffMask = 0;
        for (int i : nums)
            diffMask ^= i;

        int diffBit = 0;
        int j = 0;
        while (j < 32 && ((0x01 << j) & diffMask) == 0)
            j++;

        diffMask = diffMask & (0x01 << j);
        int diff0 = 0;
        int diff1 = 0;
        for (int i : nums) {
            if ((i & diffMask) == 0) {
                diff0 ^= i;
            }
            else {
                diff1 ^= i;
            }
        }
        int[] res = new int[2];
        res[0] = diff0;
        res[1] = diff1;
        return res;
    }
}

public class Leet260x_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 4, 5, 5, 10, 6, 6, 7, 7, 1, 8, 2, 8, 9, };
        Leet260x leet = new Leet260x();
        int[] res = leet.singleNumber(arr);
        System.out.println(res[0] + ":" + res[1]);
    }
}
