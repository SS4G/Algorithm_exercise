package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/28.
 */
import java.util.*;
class Leet565 {
    public int arrayNesting(int[] nums) {
        HashSet<Integer> set0 = new HashSet<Integer>(nums.length*2);
        for (int i = 0; i < nums.length; i++) {
            if (set0.contains(i))
                continue;
            HashSet<Integer> set1 = new HashSet<Integer>(nums.length*2);
            int tmp = nums[i];
            while (!set1.contains(tmp)) {
                set1.add(tmp);
                tmp = nums[tmp];
            }
            if (set1.size() > set0.size()) {
                set0 = set1;
            }
            if (set1.size() == nums.length)
                break;
        }
        return set0.size();
    }
}
public class Leet565_t {
    public static void main(String[] args) {
        Leet565 s = new Leet565();
        int[] nums = {5, 4, 0, 3, 1, 6, 2};
        int r = s.arrayNesting(nums);
        System.out.println(r);
    }
}
