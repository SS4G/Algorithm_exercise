package AlgorithmTraining.G55Utils.Java;
import java.util.*;
/**
 * Created by BUPT_SS4G on 2017/7/15.
 *
 */
public class LinkedListUtil {
    public static ListNode generateLinkedList(int[] arr) {
        ListNode dummyRoot = new ListNode(-1);  // dummy node
        ListNode tmp = dummyRoot;
        for (int i: arr) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        return dummyRoot.next;
    }

    public static ListNode generateLinkedList(List<Integer> arr) {
        ListNode dummyRoot = new ListNode(-1);  // dummy node
        ListNode tmp = dummyRoot;
        for (Integer i: arr) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        return dummyRoot.next;
    }

    public static void showList(ListNode head) {
        ListNode ptr = head;
        while (ptr != null) {
            System.out.print(ptr.val + " -> ");
            ptr = ptr.next;
        }
        System.out.println("None");
    }

    public static int size(ListNode head) {
        int cnt = 0;
        ListNode ptr = head;
        while (ptr != null) {
            //System.out.print(ptr.val + " -> ");
            cnt += 1;
            ptr = ptr.next;
        }
        return cnt;
    }

    public static ListNode mergeTwoAcsendingList(ListNode head0, ListNode head1) {
        //returned linklist is also acsending list
        ListNode ptr0 = head0;
        ListNode ptr1 = head1;
        ListNode newDummyHead = new ListNode(-1);
        ListNode ptrNew = newDummyHead;
        while (ptr0 != null && ptr1 != null) {
            if (ptr0.val < ptr1.val) {
                ptrNew.next = ptr0;
                ptr0 = ptr0.next;
            }
            else {
                ptrNew.next = ptr1;
                ptr1 = ptr1.next;
            }
            ptrNew = ptrNew.next;
        }
        if (ptr0 != null)
            ptrNew.next = ptr0;

        if (ptr1 != null)
            ptrNew.next = ptr1;
        return newDummyHead.next;
    }

    public static ListNode getNode(ListNode head, int offset) {
        // head is not dummy
        int offsetPtr = 0;
        ListNode ptr = head;
        for (int i = 0; i < offset; i++) {
            if (ptr == null)
                return null;
            ptr = ptr.next;
        }
        return ptr;
    }

    public static void main(String[] args) { // unitest
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {1, 3, 5, 7, 9};
        //int[] arr3 = {2, 4, 6, 8, 10};
        int[] arr3 = {};
        ListNode root = generateLinkedList(arr);
        showList(root);

        ListNode head1 = generateLinkedList(arr2);
        ListNode head2 = generateLinkedList(arr3);

        ListNode head3 = mergeTwoAcsendingList(head1, head2);
        showList(head3);

        ListNode x = getNode(head3, 2);
        //int x0 = getNode(head3, 2).val;
        System.out.println(x.val);
        //System.out.println(size(root));
    }
}

