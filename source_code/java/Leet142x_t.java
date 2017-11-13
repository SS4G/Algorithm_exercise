package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/10/13.
 */
//tested
class Leet142x {
    ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == null)
                return null;
            fast = fast.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }
        if (fast == null)
            return null;
        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

public class Leet142x_t {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        ListNode ptr = head;
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = head.next.next.next.next;
        Leet142x leet = new Leet142x();
        System.out.println(leet.detectCycle(head).val);
    }
}
