package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/11.
 */
class Leet053x {
    public int maxSubArray(int[] nums) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (curSum >= 0) {
                curSum += nums[i];
                maxSum = Math.max(maxSum, curSum);
            }
            else {
                curSum = nums[i];
                maxSum = Math.max(maxSum, curSum);
            }
        }
        return maxSum;
    }
}

public class Leet053x_t {
    public static void main(String[] args) {
        Leet053x leet = new Leet053x();
        System.out.println(leet.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
