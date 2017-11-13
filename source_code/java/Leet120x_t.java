package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/12.
 */
import java.util.*;
class Leet120x {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1)
            return triangle.get(0).get(0);
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int left = triangle.get(i + 1).get(j);
                int right = triangle.get(i + 1).get(j + 1);
                int thisVal = triangle.get(i).get(j);
                triangle.get(i).set(j, Math.min(left, right) + thisVal);
            }
        }
        return triangle.get(0).get(0);
    }
}

public class Leet120x_t {
    public static void main(String[] args) {
        Leet120x leet = new Leet120x();
        List<List<Integer>> arr = new ArrayList<>();
        Integer[][] datas = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };
        for (Integer[] intList : datas) {
            arr.add(Arrays.asList(intList));
        }
        System.out.println(leet.minimumTotal(arr));
    }
}
