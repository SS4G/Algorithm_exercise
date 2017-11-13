package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.LinkedList;

/**
 * Created by G5501 on 2017/10/13.
 */
class Leet155x {
    class MinStack {
        LinkedList<Integer> stack;
        LinkedList<Integer> minStack;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.size() == 0 || minStack.peek() > x) {
                minStack.push(x);
            }
            else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}

public class Leet155x_t {
    public static void main(String[] args) {

    }
}
