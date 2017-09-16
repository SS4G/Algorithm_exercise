package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/9.
 */

import AlgorithmTraining.G55Utils.Java.*;

class Solution017 {
    public ListNode ReverseList(ListNode head) {
		ListNode ptr = head;
		ListNode next;
        ListNode prev = null;
		while (ptr != null) {
            next = ptr.next;
            ptr.next = prev;
            prev = ptr;
            ptr = next;
        }
        return prev;
    }
}
public class No017 {
    public static void main(String[] args) {
        int[] arr0 = {1, 2, 3, 4, 5, 6};
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2};
        int[] arr3 = {1, };
        int[] arr4 = {};
        test(arr0);
        test(arr1);
        test(arr2);
        test(arr3);
        test(arr4);
    }

    private static void test(int[] arr) {
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        Solution017 s = new Solution017();
        ListNode revHead = s.ReverseList(head);
        LinkedListUtil.showList(revHead);
    }
}
