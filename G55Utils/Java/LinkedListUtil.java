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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ListNode root = generateLinkedList(arr);
        showList(root);
        System.out.println(size(root));
    }
}

