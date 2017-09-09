package AlgorithmTraining.exercise.leetcode2nd;

import java.util.Arrays;

/**
 * Created by 903 on 2017/7/22.
 */
class Leet016 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int cloest = target > 0? Integer.MAX_VALUE - 3: Integer.MIN_VALUE + 3;
        for (int i = 0; i < nums.length - 2; i ++) {
            int leftTarget = target - nums[i];
            int leftIdx = i + 1;
            int rightIdx = nums.length - 1;
            int tmpSum = 0;
            while (leftIdx < rightIdx) {
                tmpSum = nums[leftIdx] + nums[rightIdx];
                if (tmpSum < leftTarget) {
                    leftIdx ++;
                }
                else if (tmpSum > leftTarget) {
                    rightIdx --;
                }
                else {
                    System.out.println("equ");
                    return target;
                }

                cloest = Math.abs(cloest - target) > Math.abs(tmpSum - leftTarget) ?nums[i] + tmpSum: cloest;
            }
        }
        return cloest;
    }
}

public class Leet016_t {
    public static void main(String[] args) {
        Leet016 lt = new Leet016();
        int[] nums = {-1, 2, 1, -4};
        nums = new int[]{0, 1, 2};
        System.out.println(lt.threeSumClosest(nums, 0));
    }
}
