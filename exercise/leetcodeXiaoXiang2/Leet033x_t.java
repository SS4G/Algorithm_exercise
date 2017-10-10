package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
class Leet033x {
    public int search(int[] nums, int target) {
        return 0;
    }

    public int searchBreakPoint(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) {
            return -1; //no break point
        }
        else {
            int lo = 0;
            int hi = nums.length - 1;
            int mid;
            double avr = (nums[hi] + nums[lo]) / 2;
            while (lo <= hi) {
                mid = (lo + hi) >> 1;
                if (nums[mid] < nums[mid - 1]) {
                    return mid;
                }
                else if (nums[mid] > avr) {
                    hi = mid - 1;
                }
                else { //nums[mid] < avr
                    lo = mid + 1;
                }
            }
        }
        return -1;
    }
}

public class Leet033x_t {
    public static void main(String[] args) {
        Leet033x leet = new Leet033x();
        int[] nums = {5, 6, 7, 8, 1, 2, 3, 4};
        System.out.println(leet.searchBreakPoint(nums));
    }
}
