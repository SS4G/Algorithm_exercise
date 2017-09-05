package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by 903 on 2017/8/6.
 */
import AlgorithmTraining.G55Utils.Java.*;
class Leet024 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        ListNode swapPtr = dummy;
        ListNode ptr1;
        ListNode ptr2;
        ListNode ptr3;
        while (true) {
            if (swapPtr.next == null) {
                break;
            }
            else if (swapPtr.next != null && swapPtr.next.next == null) {
                break;
            }
            else {
                ptr1 = swapPtr.next;
                ptr2 = swapPtr.next.next;
                ptr3 = swapPtr.next.next.next;
                swapPtr.next = ptr2;
                swapPtr.next.next = ptr1;
                swapPtr.next.next.next = ptr3;
                swapPtr = swapPtr.next.next;
            }
        }
        return dummy.next;
    }
}

public class Leet024_t {
    public static void main(String[] args) {
        Leet024 leet = new Leet024();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        arr = new int[]{};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        LinkedListUtil.showList(head);
        LinkedListUtil.showList(leet.swapPairs(head));
    }
}
