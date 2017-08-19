package AlgorithmTraining.leetcode2nd;

/**
 * Created by 903 on 2017/8/4.
 *
 */
import java.util.*;
class Leet018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> set0 = new HashSet<>();

        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                int restTarget = target - nums[i] - nums[j];
                int lo = j + 1;
                int hi = nums.length - 1;
                while (lo < hi) {
                    int tmpSum = nums[lo] + nums[hi];
                    if (tmpSum < restTarget) {
                        lo ++;
                    }
                    else if (tmpSum > restTarget) {
                        hi --;
                    }
                    else {
                        Integer[] x = new Integer[]{nums[i], nums[j], nums[lo], nums[hi]};

                        set0.add(Arrays.asList(x));
                        lo ++;
                        hi --;
                    }
                }
            }
        }
        return new ArrayList<>(set0);
    }
}

public class Leet018_t {
    public static void main(String[] args) {
        Leet018 leet = new Leet018();
        int [] arr = {1, 0, -1, 0, -2, 2, 2, 2, 2, 2};
        List<List<Integer>> resli = leet.fourSum(arr, 0);
        System.out.println(resli);
    }
}
