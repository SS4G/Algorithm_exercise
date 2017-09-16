package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/8.
 */
import AlgorithmTraining.G55Utils.Java.*;

class Solution016 {
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode frontPtr = head;
        ListNode behindPtr;
        for (int i = 0; i < k; i++) {
            if (frontPtr == null)
                return null;
            frontPtr = frontPtr.next;
        }
        behindPtr = head;
        while (frontPtr != null) {
            frontPtr = frontPtr.next;
            behindPtr = behindPtr.next;
        }
        return behindPtr;
    }
}

public class No016 {
    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        Solution016 s = new Solution016();
        System.out.println(s.FindKthToTail(head, 1).val);
        System.out.println(s.FindKthToTail(head, 2).val);
        System.out.println(s.FindKthToTail(head, 3).val);
        System.out.println(s.FindKthToTail(head, 10));
        System.out.println(s.FindKthToTail(null, 10));
        System.out.println(s.FindKthToTail(null, 0));
    }
}
