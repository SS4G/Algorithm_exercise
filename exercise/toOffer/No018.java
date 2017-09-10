package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/9.
 */

import AlgorithmTraining.G55Utils.Java.*;
class Solution018 {
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(1);
        ListNode addPtr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                addPtr.next = list1;
                list1 = list1.next;
                addPtr = addPtr.next;
            }
            else {
                addPtr.next = list2;
                list2 = list2.next;
                addPtr = addPtr.next;
            }
        }
        while (list1 != null) {
            addPtr.next = list1;
            list1 = list1.next;
            addPtr = addPtr.next;
        }
        while (list2 != null) {
            addPtr.next = list2;
            list2 = list2.next;
            addPtr = addPtr.next;
        }
        return dummy.next;
    }
}

public class No018 {
    public static void main(String[] args) {
        int[] arr0 = {1, 3, 5, 7, 9};
        int[] arr1 = {2, 4, 6, 8, 10};
        arr0 = new int[]{};
        arr1 = new int[]{};
        ListNode l1 = LinkedListUtil.generateLinkedList(arr0);
        ListNode l2 = LinkedListUtil.generateLinkedList(arr1);
        Solution018 s = new Solution018();
        LinkedListUtil.showList(s.Merge(l1, l2));
    }
}
