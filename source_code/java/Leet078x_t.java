package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/11.
 */
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.*;
class Leet078x {
    public List<List<Integer>> subsets(int[] nums) {
        int mask = 0xffffffff;
        int i = 0;
        int limMask = mask >>> (32 - nums.length);
        List<List<Integer>> result = new ArrayList<>();
        while (i <= limMask) {
            result.add(mapTo(nums, i));
            i++;
        }
        return result;
    }

    private List<Integer> mapTo(int[] nums, int val) {
        List<Integer> res = new ArrayList<>();
        int mask = 0x01;
        for (int i = 0; i < nums.length; i++, mask <<= 1) {
            if ((mask & val) != 0) {
                res.add(nums[i]);
            }
        }
        return res;
    }
}

public class Leet078x_t {
    public static void main(String[] args) {
        Leet078x leet = new Leet078x();
        //System.out.println(leet.mapTo(new int[]{1, 2, 3}, 0));
        int[] arr = {1, 2, 3};
        System.out.println(leet.subsets(arr));
    }
}
