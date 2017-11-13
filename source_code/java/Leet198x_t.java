package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/13.
 */
class Leet198x {
    int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1] > nums[0] ? nums[1] : nums[0];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}

public class Leet198x_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 1, 5, 6, 2, 3};
        Leet198x leet = new Leet198x();
        System.out.println(leet.rob(arr));
    }
}
