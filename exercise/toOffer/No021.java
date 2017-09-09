package AlgorithmTraining.exercise.toOffer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by szh-920 on 17-9-9.
 *
 */
class Solution021 {
    private LinkedList<Integer> stack = new LinkedList<>();
    private LinkedList<Integer> minStack = new LinkedList<Integer>();
    private int minVal = Integer.MAX_VALUE;
    public void push(int node) {
        stack.push(node);
        if (node <= minVal) {
            minStack.push(node);
            minVal = node;
        }
        else
            minStack.push(minVal);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
        if (minStack.size() > 0) {
            minVal = minStack.get(0);
        }
        else {
            minVal = Integer.MAX_VALUE;
        }
    }

    public int top() {
        return stack.get(stack.size() - 1);
    }

    public int min() {
        return minVal;
    }
}

public class No021 {
    public static void main(String[] args) {
        Solution021 s = new Solution021();
        //["PSH3","MIN","PSH4","MIN","PSH2","MIN","PSH3","MIN","POP","MIN","POP","MIN","POP","MIN","PSH0","MIN"]

        s.push(3);
        System.out.println(s.min());
        s.push(4);
        System.out.println(s.min());
        s.push(2);
        System.out.println(s.min());
        s.push(3);
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        s.push(0);
        System.out.println(s.min());
    }
}
