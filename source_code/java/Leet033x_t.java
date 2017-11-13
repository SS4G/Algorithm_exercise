package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
class Leet033x {
    public int search(int[] nums, int target) {
        int breakPoint = searchBreakPoint(nums);
        if (breakPoint == -1) {
            //System.out.println("tmpfile");
            return binSearch(nums, 0, nums.length, target);
        }
        else {
            //System.out.println("bk = " + breakPoint);
            int a = binSearch(nums, 0, breakPoint, target);
            if (a != -1)
                return a;
            return binSearch(nums, breakPoint, nums.length, target);
        }
    }

    public int searchBreakPoint(int[] nums) {
        if (nums.length <= 1 || nums[0] < nums[nums.length - 1]) {
            return -1; //no break point
        }
        else {
            int lo = 0;
            int hi = nums.length - 1;
            int mid;
            double avr = (nums[hi] + nums[lo]) / 2;
            while (lo <= hi) {
                mid = (lo + hi) >> 1;
                //System.out.println("lo=" + lo + " hi=" + hi + " mid=" + mid);
                if (mid == 0 && nums[mid] > nums[mid + 1]) {
                    return mid + 1;
                }
                else if (mid == 0) {
                    return -1;
                }
                else if (nums[mid] < nums[mid - 1]) {
                    return mid;
                }
                else if (nums[mid] > avr) {
                    lo = mid + 1;
                }
                else { //nums[mid] < avr
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    public int binSearch(int[] nums, int bg, int ed, int target) {
        int lo = bg;
        int hi = ed - 1;
        int mid = (lo + hi) >> 1;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (nums[mid] > target) {
                hi = mid - 1;
            }
            else if (nums[mid] < target) {
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
}

public class Leet033x_t {
    public static void main(String[] args) {
        Leet033x leet = new Leet033x();
        int[] nums = {5, 6, 7, 8, 1, 2, 3, 4};
        //nums = new int[]{1, 2, 3, 4, 5, 6};
        nums = new int[]{1};
        nums = new int[]{2, 1};
        nums = new int[]{3, 5, 1};
        //System.out.println(leet.searchBreakPoint(nums));
        //System.out.println(leet.search(nums, 1));
        //System.out.println(leet.search(nums, 8));
        //System.out.println(leet.search(nums, 5));
        //System.out.println(leet.search(nums, 2));
        //System.out.println(leet.search(nums, 1));
        System.out.println(leet.search(nums, 0));
    }
}
