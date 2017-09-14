package AlgorithmTraining.exercise.toOffer;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/9/14.
 */
class Solution065 {
    PriorityQueue<Integer> maxPart = new PriorityQueue<>();
    PriorityQueue<Integer> minPart = new PriorityQueue<>(); // need to convert to neg
    private double median = 0;

    public void Insert(Integer num) {
        if (num >= median) {
            maxPart.offer(num);
        }
        else {
            minPart.offer(-num);
        }
        //rebalance
        if (maxPart.size() - minPart.size() >= 2) {
            minPart.offer(-maxPart.poll());
        }
        else if (minPart.size() - maxPart.size() >= 2) {
            maxPart.offer(-minPart.poll());
        }

        if (maxPart.size() - minPart.size() == 1) {
            median = maxPart.peek();
        }
        else if (minPart.size() - maxPart.size() == 1) {
            median = -minPart.peek();
        }
        else if (minPart.size() == maxPart.size()) {
            median = (new Double(maxPart.peek()) + new Double(-minPart.peek())) / 2;
        }
    }

    public Double GetMedian() {
        return median;
    }
}

public class No065 {
    public static void main(String[] args) {
        Solution065 s = new Solution065();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        arr = new int[]{9, 8, 2, 3, 4, 5, 10, 13, 8};
        for (int i : arr) {
            s.Insert(i);
            System.out.println(s.GetMedian());
        }
    }
}
