package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */

class Leet024x {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        ListNode swapPtr = dummy;
        while (swapPtr != null) {
            swapPtr = swap(swapPtr);
        }
        return dummy.next;
    }

    private ListNode swap(ListNode begin) { //begin -> l1 -> l2 return l2 if l2 exist else return null
        ListNode l1 = begin.next;
        ListNode l2;
        ListNode l3;
        if (l1 == null)
            return null;
        else {
            l2 = l1.next;
            if (l2 == null)
                return null;
        }
        l3 = l2.next;
        begin.next = l2;
        l2.next = l1;
        l1.next = l3;
        return l1;
    }
}

public class Leet024x_t {
    public static void main(String[] args) {
        ListNode head0 = LinkedListUtil.generateLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode head1 = LinkedListUtil.generateLinkedList(new int[]{1, 2, 3, 4});
        ListNode head2 = LinkedListUtil.generateLinkedList(new int[]{1, 2});
        ListNode head3 = LinkedListUtil.generateLinkedList(new int[]{1, 2, 3});
        ListNode head4 = LinkedListUtil.generateLinkedList(new int[]{2, });
        ListNode head5 = LinkedListUtil.generateLinkedList(new int[]{});
        Leet024x leet = new Leet024x();
        //leet.swap(head3);
        //LinkedListUtil.showList(head3);

        LinkedListUtil.showList(leet.swapPairs(head0));
        LinkedListUtil.showList(leet.swapPairs(head1));
        LinkedListUtil.showList(leet.swapPairs(head2));
        LinkedListUtil.showList(leet.swapPairs(head3));
        LinkedListUtil.showList(leet.swapPairs(head4));
        LinkedListUtil.showList(leet.swapPairs(head5));
    }
}
