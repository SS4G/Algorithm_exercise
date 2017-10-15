package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/15.
 */

import java.util.*;
class MyQueue {
    LinkedList<Integer> stack0;// = new LinkedList<>();
    LinkedList<Integer> stack1;// = new LinkedList<>();
    boolean pos0;
    boolean pos1;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack0 = new LinkedList<>();
        stack1 = new LinkedList<>();
        pos0 = false;
        pos1 = false;
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (!pos0)
            stack0.addLast(x);
        else
            stack1.addLast(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int res = 0;
        if (pos0) {
            res = stack0.removeLast();
            pos0 = stack0.size() > 0;
        }
        else if (pos1) {
            res = stack1.removeLast();
            pos1 = stack1.size() > 0;
        }
        else if (stack0.size() > 0) {
            while (stack0.size() > 1) {
                stack1.addLast(stack0.removeLast());
                pos1 = true;
            }
            res = stack0.removeLast();
            pos0 = false;
        }
        else if (stack1.size() > 0) {
            while (stack1.size() > 1) {
                stack0.addLast(stack1.removeLast());
                pos0 = true;
            }
            res = stack1.removeLast();
            pos1 = false;
        }
        return res;
    }

    /** Get the front element. */
    public int peek() {
        if (pos0) {
            return stack0.getLast();
        }
        else if (pos1) {
            return stack1.getLast();
        }
        else if (stack0.size() > 0) {
            while (stack0.size() > 0) {
                stack1.addLast(stack0.removeLast());
                pos1 = true;
            }
            pos0 = false;
            return stack1.getLast();
        }
        else if (stack1.size() > 0) {
            while (stack1.size() > 0) {
                stack0.addLast(stack1.removeLast());
                pos0 = true;
            }
            pos1 = false;
            return stack0.getLast();
        }
        return 0; // can't reach
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.size() == 0 && stack0.size() == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(stack0.toString() + ":" + pos0 + "  ");
        sb.append(stack1.toString() + ":" + pos1);
        return sb.toString();
    }
}
public class Leet232x_t {
    public static void main(String[] args) {
        MyQueue mq = new MyQueue();
        mq.push(1);
        //System.out.println(mq);
        mq.push(2);
        //System.out.println(mq);
        mq.push(3);
        //System.out.println(mq);
        assert mq.pop() == 1;
        //System.out.println(mq);
        mq.push(4);
        //System.out.println(mq);
        mq.push(5);
        //System.out.println(mq);
        assert mq.pop() == 2;
       // System.out.println(mq);
        assert mq.pop() == 3;
        assert mq.pop() == 4;
        System.out.println(mq);
        mq.push(6);
        System.out.println(mq);
        assert mq.peek() == 5;
        System.out.println(mq);
        mq.push(8);
        System.out.println(mq);
        mq.push(9);
        System.out.println(mq);
        mq.pop();
        assert mq.peek() == 6;
        assert !mq.empty();
        mq.pop();
        mq.pop();
        mq.pop();
        assert mq.empty();
    }
}
