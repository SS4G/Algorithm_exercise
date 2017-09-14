package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

import java.util.*;

/**
 * Created by G5501 on 2017/9/10.
 */

class Solution036 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        if (pHead1 == null || pHead2 == null)
            return null;
        ListNode ptr1 = pHead1;
        ListNode ptr2 = pHead2;
        while (ptr1 != null) {
            stack1.push(ptr1);
            ptr1 = ptr1.next;
        }
        while (ptr2 != null) {
            stack2.push(ptr2);
            ptr2 = ptr2.next;
        }
        ListNode p1, p2;
        ListNode lastCommon = null;
        while (stack1.size() > 0 && stack2.size() > 0 && (p1 = stack1.pop()) == (p2 = stack2.pop())) {
            lastCommon = p1;
        }
        return lastCommon;
    }
}

public class No036 {
    public static void main(String[] args) {

    }
}
