package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/9/13.
 */

class Solution053 {
    public ListNode deleteDuplication(ListNode pHead)
    {
        ListNode ptr;
        ListNode dummy = new ListNode(Integer.MAX_VALUE - 324421);
        ptr = dummy;
        dummy.next = pHead;
        while (ptr != null) {
            if (isDup(ptr.next)) {
                ListNode tmp = ptr.next;
                int val = tmp.val;
                while (tmp != null && tmp.val == val) {
                    tmp = tmp.next;
                }
                ptr.next = tmp;
            }
            else {
                ptr = ptr.next;
            }
        }
        return dummy.next;
    }

    private boolean isDup(ListNode ptr) {
        if (ptr == null)
            return false;
        if (ptr.next == null)
            return false;
        else if (ptr.val == ptr.next.val)
            return true;
        else
            return false;
    }
}

public class No053 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 4, 4, 4, 5, 6};
        arr = new int[]{1, 2, 3, 4, 5, 6};
        arr = new int[]{1, 2, 3, 3, 3, 4, 4, 4, 5, 6, 6};
        arr = new int[]{1, 1, 1, 2, 2};
        arr = new int[]{};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        Solution053 s = new Solution053();
        head = s.deleteDuplication(head);
        LinkedListUtil.showList(head);
    }
}
