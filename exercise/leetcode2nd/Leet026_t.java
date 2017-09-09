package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by 903 on 2017/8/6.
 */
class Leet026 {
    public int removeDuplicates(int[] nums) {
        int rPtr = 0;
        int wPtr = 0;
        int lastValue = Integer.MIN_VALUE;
        while (rPtr < nums.length) {
            if (nums[rPtr] != lastValue) {
                nums[wPtr] = nums[rPtr];
                lastValue = nums[rPtr];
                wPtr++;
            }
            else if (rPtr == 0 && nums[0] == Integer.MIN_VALUE)
                wPtr++;
            rPtr++;
        }
        return wPtr;
    }
}

public class Leet026_t {
    public static void main(String[] args) {
        Leet026 leet = new Leet026();
        int[] nums = {1, 2, 2, 2, 3, 4, 4, 5, 5, 5, 6, 6, 7, 8, 9};
        int b = leet.removeDuplicates(nums);
        for (int i = 0; i < b; i++) {
            System.out.println(nums[i]);
        }
    }
}
