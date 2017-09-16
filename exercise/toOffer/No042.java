package AlgorithmTraining.exercise.toOffer;

import java.util.*;

/**
 * Created by G5501 on 2017/9/11.
 */
class Solution042 {
    public String LeftRotateString(String str,int n) {
        if (str.length() <= 0)
            return "";
        char[] arr = str.toCharArray();
        Queue<Character> fifo = new LinkedList<>();
        for (char x : arr) {
            fifo.add(x);
        }
        for (int i = 0; i < n; i++) {
            fifo.offer(fifo.poll());
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : fifo) {
            sb.append(c);
        }
        return sb.toString();
    }
}

public class No042 {
    public static void main(String[] args) {

    }
}
