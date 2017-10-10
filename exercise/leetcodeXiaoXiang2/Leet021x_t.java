package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
import AlgorithmTraining.G55Utils.Java.*;
class Leet021x {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode addPtr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                addPtr.next = l1;
                l1 = l1.next;
            }
            else {
                addPtr.next = l2;
                l2 = l2.next;
            }
            addPtr = addPtr.next;
        }

        while (l1 != null) {
            addPtr.next = l1;
            l1 = l1.next;
            addPtr = addPtr.next;
        }

        while (l2 != null) {
            addPtr.next = l2;
            l2 = l2.next;
            addPtr = addPtr.next;
        }

        return dummy.next;
    }
}

public class Leet021x_t {
    public static void main(String[] args) {
        int[] arr0 = {1, 3, 5, 7, 9, };
        int[] arr1 = {0, 2, 4, 6, 8, };
        ListNode l1 = LinkedListUtil.generateLinkedList(arr0);
        ListNode l2 = LinkedListUtil.generateLinkedList(arr1);
        Leet021x leet = new Leet021x();
        ListNode head = leet.mergeTwoLists(l1, l2);
        LinkedListUtil.showList(head);
    }
}
