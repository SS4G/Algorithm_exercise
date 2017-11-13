package AlgorithmTraining.exercise.leetcode2nd;

/**
 *
 * Created by 903 on 2017/8/5.
 *
 */

import AlgorithmTraining.G55Utils.Java.*;

class Leet021 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyRes = new ListNode(3);
        ListNode ptr1 = l1; //dummy1;
        ListNode ptr2 = l2; //dummy2;
        ListNode ptrRes = dummyRes;
        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val > ptr2.val) {
                ptrRes.next = ptr2;
                ptr2 = ptr2.next;
            }
            else {
                ptrRes.next = ptr1;
                ptr1 = ptr1.next;
            }
            ptrRes = ptrRes.next;
        }

        while (ptr1 != null) {
            ptrRes.next = ptr1;
            ptr1 = ptr1.next;
            ptrRes = ptrRes.next;
        }

        while (ptr2 != null) {
            ptrRes.next = ptr2;
            ptr2 = ptr2.next;
            ptrRes = ptrRes.next;
        }
        return dummyRes.next;
    }
}

public class Leet021_t {
    public static void main(String[] args) {
        int[] arr0 = {1, 3, 5, 7, 9,};
        arr0 = new int[]{};
        int[] arr1 = {};
        ListNode l1 = LinkedListUtil.generateLinkedList(arr0);
        ListNode l2 = LinkedListUtil.generateLinkedList(arr1);
        Leet021 leet = new Leet021();
        ListNode l3 = leet.mergeTwoLists(l1, l2);
        LinkedListUtil.showList(l3);
    }
}
