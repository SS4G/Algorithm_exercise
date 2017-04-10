package AlgorithmTraining.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/4/10.
 */
class Solution477 {
    private int calcbit1s(int k) {
        int sum = 0;
        for(int i = 0; i < 32; i++)
            sum += ((1 << i)&k)!=0 ? 1: 0;
        return sum;
    }
    public int totalHammingDistance(int[] nums) {
        int totalHammings = 0;
        int half = nums.length >>> 1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if (i != j) {
                    totalHammings += calcbit1s(nums[i] ^ nums[j]);
                }
            }
        }
        return totalHammings;
    }
}
public class Leet477 {
    public static void main(String[] args) {
         int[] arr = {4, 14, 2};
         Solution477 x = new Solution477();
         System.out.println(x.totalHammingDistance(arr));
    }
}
