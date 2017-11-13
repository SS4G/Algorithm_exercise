package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/15.
 */

import java.util.*;
class MyStack {
    Queue<Integer> qu0;
    Queue<Integer> qu1;
    boolean using0;
    /** Initialize your data structure here. */
    public MyStack() {
        qu0 = new LinkedList<>();
        qu1 = new LinkedList<>();
        using0 = false;
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (!using0) {
            qu1.offer(x);
        }
        else {
            qu0.offer(x);
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (using0) {
            while (qu0.size() > 1) {
                qu1.offer(qu0.poll());
            }
            using0 = false;
            return qu0.poll();
        }
        else {
            while (qu1.size() > 1) {
                qu0.offer(qu1.poll());
            }
            using0 = true;
            return qu1.poll();
        }
    }

    /** Get the top element. */
    public int top() {
        int topElement = pop();
        push(topElement);
        return topElement;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return qu0.size() == 0 && qu1.size() == 0;
    }
}

public class Leet225x_t {
    public static void main(String[] args) {
        MyStack mst = new MyStack();
        mst.push(1);
        assert mst.top() == 1;
        mst.push(2);
        mst.push(3);
        mst.push(4);
        assert mst.top() == 4;
        assert mst.top() == 4;
        assert mst.pop() == 4;
        assert mst.pop() == 3;

    }
}