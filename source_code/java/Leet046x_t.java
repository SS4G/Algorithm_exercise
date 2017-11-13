package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/11.
 */
import java.util.*;

class Leet046x {
    public List<List<Integer>> permute(int[] nums) {
        boolean[] mark = new boolean[nums.length];
        List<List<Integer>> output = new ArrayList<>();
        placeNum(output, new LinkedList<Integer>(), nums, mark, 0);
        return output;
    }

    private void placeNum(List<List<Integer>> output, LinkedList<Integer> tmpOutput, int[] nums, boolean[] mark, int step) {
        if (step >= mark.length) {
            output.add(new ArrayList<>(tmpOutput));
        }
        else {
            for (int i = 0; i < nums.length; i++) {
                if (!mark[i]) {
                    mark[i] = true;
                    tmpOutput.push(nums[i]);
                    placeNum(output, tmpOutput, nums, mark, step + 1);
                    tmpOutput.pop();
                    mark[i] = false;
                }
            }
        }
    }
}

public class Leet046x_t {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Leet046x leet = new Leet046x();
        List<List<Integer>> res = leet.permute(nums);
        System.out.println(res);
    }
}
