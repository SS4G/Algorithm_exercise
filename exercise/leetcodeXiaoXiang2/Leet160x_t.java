package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.LinkedListUtil;
import AlgorithmTraining.G55Utils.Java.ListNode;

import java.util.List;

/**
 * Created by G5501 on 2017/10/13.
 */
//tested
class Leet160x {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = size(headA);
        int sizeB = size(headB);
        if (sizeA == 0 || sizeB == 0)
            return null;
        if (sizeA < sizeB) {
            ListNode tmp = headA;
            headA = headB;
            headB = tmp;
        }
        ListNode tmpA = headA;
        ListNode tmpB = headB;
        for (int i = 0; i < sizeA - sizeB; i++) {
            tmpA = tmpA.next;
        }
        while (tmpA != null && tmpB != null && tmpA != tmpB) {
            tmpA = tmpA.next;
            tmpB = tmpB.next;
        }

        if (tmpA == null || tmpB == null) {
            return null;
        }
        else
            return tmpA;
    }

    public int size(ListNode head) {
        int cnt = 0;
        ListNode ptr = head;
        while (ptr != null) {
            //System.out.print(ptr.val + " -> ");
            cnt += 1;
            ptr = ptr.next;
        }
        return cnt;
    }
}

public class Leet160x_t {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr2 = {8, 8, 9};
        ListNode headA = LinkedListUtil.generateLinkedList(arr1);
        ListNode headB = LinkedListUtil.generateLinkedList(arr2);

        Leet160x leet = new Leet160x();
        System.out.println(leet.getIntersectionNode(headA, headA.next).val);
    }
}
