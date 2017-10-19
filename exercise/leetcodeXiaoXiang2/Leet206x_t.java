package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

import java.util.List;

/**
 * Created by G5501 on 2017/10/14.
 */

class Leet206x {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        ListNode tailDummy = new ListNode(Integer.MAX_VALUE);
        ListNode ptr = dummy.next;
        while (ptr != null) {
            dummy.next = ptr.next;
            ptr.next = tailDummy.next;
            tailDummy.next = ptr;
            ptr = dummy.next;
        }

        return tailDummy.next;
    }
}

public class Leet206x_t {
    public static void main(String[] args) {
        int[] arr = {1};
        ListNode arr0 = LinkedListUtil.generateLinkedList(arr);
        Leet206x leet = new Leet206x();
        arr0 = leet.reverseList(arr0);
        LinkedListUtil.showList(arr0);
    }
}
