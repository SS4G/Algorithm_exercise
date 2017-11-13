package AlgorithmTraining.exercise.leetcode2nd;

import java.util.Arrays;

/**
 * Created by BUPT_SS4G on 2017/9/19.
 */
class Leet031 {
    public void nextPermutation(int[] nums) {
        if (nums.length >= 2) {
            if (nums[nums.length - 1] > nums[nums.length - 2]) {
                int tmp = nums[nums.length - 1];
                nums[nums.length - 1] = nums[nums.length - 2];
                nums[nums.length - 2] = tmp;
            }
            else {
                int tailIdx = findFirstLittleThan(nums);
                //System.out.println(tailIdx);
                if (tailIdx >= 0) {
                    int res = findNextGreater(nums, tailIdx, nums.length, nums[tailIdx]);
                    int tmp = nums[res];
                    nums[res] = nums[tailIdx];
                    nums[tailIdx] = tmp;
                    Arrays.sort(nums, tailIdx + 1, nums.length);
                }
                else {
                    Arrays.sort(nums, 0, nums.length);
                }
            }
        }
    }

    private int findFirstLittleThan(int[] nums) {
        assert nums.length >= 2;
        int lastVal = Integer.MIN_VALUE;
        int tailIdx = nums.length - 1;
        while (tailIdx >= 0 && nums[tailIdx] >= lastVal) {
            lastVal = nums[tailIdx];
            tailIdx--;
        }
        return tailIdx;
    }

    private int findNextGreater(int[] nums, int from, int end, int stdVal) {
        int nextGreater = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = from; i < end; i++) {
            if (nums[i] > stdVal && nums[i] < nextGreater) {
                nextGreater = nums[i];
                idx = i;
            }
        }
        return idx;
    }
}

public class Leet031_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 4, 3};
        Leet031 leet = new Leet031();
        leet.nextPermutation(arr);
        showArr(arr);
    }

    private static void showArr(int[] nums) {
        StringBuilder sb  = new StringBuilder();
        for (int i : nums) {
            sb.append(i + ":");
        }
        System.out.println(sb.toString());
    }
}
