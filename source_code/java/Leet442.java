package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/9.
 */
import java.util.*;
import java.lang.Math;
public class Leet442 {
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new LinkedList();
        for(int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] < 0) {
                res.add(Math.abs(nums[i]));
            }
            else {
                nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] a = {4,3,2,7,8,2,3,1};
        for(Integer i : findDuplicates(a)) {
            System.out.println(i);
        }
    }
}
