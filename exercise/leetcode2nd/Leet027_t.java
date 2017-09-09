package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by 903 on 2017/8/6.
 */
class Leet027 {
    public int removeElement(int[] nums, int val) {
        int wPtr = 0;
        int rPtr = 0;
        for (rPtr = 0; rPtr < nums.length; rPtr++) {
            if (nums[rPtr] != val) {
                nums[wPtr] = nums[rPtr];
                wPtr++;
            }
        }
        return wPtr;
    }
}

public class Leet027_t {
    public static void main(String[] args) {
        Leet027 leet = new Leet027();
        int[] arr = {1, 2, 3, 4, 5, 6, 4, 7, 8};
        int b = leet.removeElement(arr, 4);
        for (int i = 0; i < b; i++) {
            System.out.println(arr[i]);
        }
    }
}
