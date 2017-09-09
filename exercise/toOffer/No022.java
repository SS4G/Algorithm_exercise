package AlgorithmTraining.exercise.toOffer;

import java.util.LinkedList;

/**
 * Created by szh-920 on 17-9-9.
 */
class Solution022 {
    public boolean IsPopOrder(int [] pushA, int [] popA) {
        LinkedList<Integer> stack = new LinkedList<>();
        int pushPtr = 0;
        int popPtr = 0;
        while (popPtr < popA.length) {
            if ((stack.size() > 0 && stack.peek() != popA[popPtr]) || stack.size() <= 0) {
                if (pushPtr == pushA.length)
                    return false;
                while (pushA[pushPtr] != popA[popPtr]) {
                    stack.push(pushA[pushPtr]);
                    pushPtr++;
                    if (pushPtr == pushA.length)
                        return false;
                }
                stack.push(pushA[pushPtr]);
                pushPtr++;

            }
            else {
                stack.pop();
                popPtr++;
            }
        }
        return true;
    }
}

public class No022 {
    public static void main(String[] args) {
        int[] pushA = {1, 2, 3, 4, 5};
        int[] popA = {5, 4, 3, 2, 1};
        //popA = new int[]{4, 5, 3, 2, 1};
        popA = new int[]{4, 3, 5, 1, 2};
        Solution022 s = new Solution022();
        System.out.println(s.IsPopOrder(pushA, popA));
    }
}
