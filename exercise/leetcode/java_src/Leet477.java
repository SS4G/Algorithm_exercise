package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/4/10.
 */
class Solution477 {
    private int[] bitCnt = new int[32];

    private void calcbit1s(int k) {
        for(int i = 0; i < 32; i++) {
            bitCnt[i] += (k & 0x00000001) != 0 ? 1 : 0;
            k >>>= 1;
        }
    }
    public int totalHammingDistance(int[] nums) {

        int sum = 0;
        for (int i = 0; i < 32; i++) {
            bitCnt[i] = 0;
        }
        for (int k : nums) {
            calcbit1s(k);
        }
        for (int j = 0; j < 32; j++) {
            sum += bitCnt[j] * (nums.length - bitCnt[j]);
        }
        return sum;
    }
}
public class Leet477 {
    public static void main(String[] args) {
         int[] arr = {1337, 7331};
         Solution477 x = new Solution477();
         System.out.println(x.totalHammingDistance(arr));
    }
}
