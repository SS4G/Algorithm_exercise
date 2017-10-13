package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/13.
 */
class Leet136x {
    public int singleNumber(int[] nums) {
        int j = 0;
        for (int k : nums) {
            j ^= k;
        }
        return j;
    }
}

public class Leet136x_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 2, 1, 5, 5, 7};
        Leet136x leet = new Leet136x();
        System.out.println(leet.singleNumber(arr));
    }
}
