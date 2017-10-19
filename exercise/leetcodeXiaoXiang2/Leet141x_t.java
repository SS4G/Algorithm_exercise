package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/10/13.
 */
class Leet141x {
    boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        if (head.next == null)
            return false;
        if (head.next == head)
            return true;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == null)
                return false;
            fast = fast.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }
}

public class Leet141x_t {
    public static void main(String[] args) {

    }
}
