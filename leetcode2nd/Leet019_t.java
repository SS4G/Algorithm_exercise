package AlgorithmTraining.leetcode2nd;

/**
 * Created by 903 on 2017/8/4.
 *
 */
import AlgorithmTraining.G55Utils.Java.*;

import java.util.LinkedList;

class Leet019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        ListNode prev = dummy;
        for (int i = 0; i < n; i ++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            //prev = slow;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

public class Leet019_t {
    public static void main(String[] args) {
        int[] arr = {1};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        Leet019 leet = new Leet019();
        ListNode head2 = leet.removeNthFromEnd(head, 1);
        LinkedListUtil.showList(head2);
    }
}
