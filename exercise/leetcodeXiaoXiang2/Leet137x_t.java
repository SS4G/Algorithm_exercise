package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.HashMap;

/**
 * Created by BUPT_SS4G on 2017/10/13.
 */
class Leet137x {
    public int singleNumber(int[] nums) {
        int[] bitCnt = new int[32];
        for (int i = 0; i < 32; i++)
            bitCnt[i] = 0;

        for (int i : nums) {
            for (int j = 0; j < 32; j++) {
                if (((0x01 << j) & i) != 0) {
                    bitCnt[j]++;
                }
            }
        }
        int res = 0;
        for (int j = 0; j < 32; j++) {
            if (bitCnt[j] % 3 != 0) {
                res |= (0x01 << j);
            }
        }
        return res;
    }
}

public class Leet137x_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5, 5, 4, 3, 4, 5, 3, 1, 8, 1};
        Leet137x leet = new Leet137x();
        System.out.println(leet.singleNumber(arr));
    }
}
