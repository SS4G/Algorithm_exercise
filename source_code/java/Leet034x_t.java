package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/11.
 */
class Leet034x {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = searchLeft(nums, target);
        res[1] = searchRight(nums, target);
        return res;
    }

    int searchLeft(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            }
            else if (nums[mid] > target) {
                hi = mid - 1;
            }
            else {
                if (mid == 0)
                    return 0;
                else if (nums[mid - 1] == target) {
                    hi = mid - 1;
                }
                else {
                    return mid;
                }
            }
        }
        return -1;
    }

    int searchRight(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            }
            else if (nums[mid] > target) {
                hi = mid - 1;
            }
            else {
                if (mid == nums.length - 1)
                    return nums.length - 1;
                else if (nums[mid + 1] == target) {
                    lo = mid + 1;
                }
                else {
                    return mid;
                }
            }
        }
        return -1;
    }
}
public class Leet034x_t {
    public static void main(String[] args) {
        Leet034x leet = new Leet034x();
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
        int[] res = leet.searchRange(arr, 3);
        res = leet.searchRange(arr, 1);
        System.out.println(res[0] + ":" + res[1]);
        int[] arr2 = {1, 1};
        res = leet.searchRange(arr2, 1);
        System.out.println(res[0] + ":" + res[1]);
    }
}
