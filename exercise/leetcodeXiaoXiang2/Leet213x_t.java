package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/14.
 */
class Leet213x {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        int[] dpHas0 = new int[nums.length];
        int[] dpHas1 = new int[nums.length];
        dpHas0[0] = nums[0];
        dpHas0[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        //dpHas1[0] = nums[0];
        dpHas1[1] = nums[1];
        dpHas1[2] = nums[1] > nums[2] ? nums[1] : nums[2];

        for (int i = 2; i < nums.length; i++) {
            dpHas0[i] = Math.max(dpHas0[i - 1], dpHas0[i - 2] + nums[i]);
        }

        for (int i = 3; i < nums.length; i++) {
            dpHas1[i] = Math.max(dpHas1[i - 1], dpHas1[i - 2] + nums[i]);
        }
        return Math.max(dpHas0[nums.length - 2], dpHas1[nums.length - 1]);
    }
}

public class Leet213x_t {
    public static void main(String[] args) {
        int[] arr0 = {10, 1, 5, 2, 7, 1};
        int[] arr1 = {0, 3, 1, 5, 2, 7};
        Leet213x leet = new Leet213x();
        System.out.println(leet.rob(arr0));
        System.out.println(leet.rob(arr1));
    }
}
