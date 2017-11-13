package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/14.
 */
import java.util.*;
class Leet215x {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : nums)
            pq.offer(-i);
        int res = 0;
        for (int i = 0; i < k; i++)
            res = pq.poll();
        return -res;
    }
}

public class Leet215x_t {
    public static void main(String[] args) {
        Leet215x leet = new Leet215x();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, };
        System.out.println(leet.findKthLargest(arr, 3));
    }
}
