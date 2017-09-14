package AlgorithmTraining.exercise.toOffer;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/9/14.
 */

class Solution064 {
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        if (size == 0)
            return new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        Deque<Integer> defifo = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            while (defifo.size() > 0 && defifo.getFirst() <= i - size) {//clear the element outside the window
                defifo.pollFirst();
            }

            while (defifo.size() > 0 && num[defifo.getLast()] < num[i]) {//
                defifo.pollLast();
            }
            defifo.offer(i);
            if (i >= size - 1)
                arr.add(num[defifo.getFirst()]);
        }
        return arr;
    }
}

public class No064 {
    public static void main(String[] args) {
        Solution064 s = new Solution064();
        int[] arr = {2, 3, 4, 2, 6, 2, 5, 1};
        System.out.println(s.maxInWindows(arr, 3));
        System.out.println(s.maxInWindows(arr, 1));
    }
}
