package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by BUPT_SS4G on 2017/10/12.
 */

class Leet086x {
    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(1);
        ListNode greaterDummy = new ListNode(2);
        ListNode lessPtr = lessDummy, greaterPtr = greaterDummy, rdPtr = head;
        while (rdPtr != null) {
            if (rdPtr.val < x) {
                lessPtr.next = rdPtr;
                lessPtr = lessPtr.next;
            }
            else {
                greaterPtr.next = rdPtr;
                greaterPtr = greaterPtr.next;
            }
            rdPtr = rdPtr.next;
        }
        greaterPtr.next = null;
        lessPtr.next = greaterDummy.next;
        return lessDummy.next;
    }
}

public class Leet086x_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 5, 7, 4, 1, 3};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        Leet086x leet = new Leet086x();
        //LinkedListUtil.showList(head);
        leet.partition(head, 4);
        LinkedListUtil.showList(leet.partition(head, 4));

    }
}
