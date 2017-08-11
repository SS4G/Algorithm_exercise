package AlgorithmTraining.leetcode2nd;

/**
 * Created by BUPT_SS4G on 2017/7/18.
 */
import AlgorithmTraining.G55Utils.Java.*;
import java.util.ArrayList;
import java.util.Collections;

class Leet002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<Integer> arr0 = new ArrayList<>(10000);
        ArrayList<Integer> arr1 = new ArrayList<>(10000);
        ListNode ptr = l1;
        while (ptr != null) {
            arr0.add(ptr.val);
            ptr = ptr.next;
        }
        ptr = l2;
        while (ptr != null) {
            arr1.add(ptr.val);
            ptr = ptr.next;
        }
        if (arr0.size() > arr1.size()) {
            ArrayList<Integer> tmparr = arr0;
            arr0 = arr1;
            arr1 = tmparr;
        }
        // reassure arr1.size >= arr0.size
        ArrayList<Integer> arrRes = new ArrayList<>(arr1.size() + 1);
        //Collections.reverse(arr0);
        //Collections.reverse(arr1);
        int index0 = 0;
        int index1 = 0;
        int carry = 0;
        int sum = 0;
        while (index0 < arr0.size()) {
            sum = arr0.get(index0) + arr1.get(index0) + carry;
            arrRes.add(sum % 10);
            carry = sum / 10;
            index0 ++;
        }
        index1 = index0;
        while (index1 < arr1.size()) {
            sum = arr1.get(index1) + carry;
            arrRes.add(sum % 10);
            carry = sum / 10;
            index1 ++;
        }
        if (carry != 0) {
            arrRes.add(carry);
        }
        //Collections.reverse(arrRes);
        ListNode dummyHead = new ListNode(-1);
        ptr = dummyHead;
        for (Integer i: arrRes) {
            ptr.next = new ListNode(i);
            ptr = ptr.next;
        }
        return dummyHead.next;
    }
}

public class Leet002_t {
    public static void main(String[] args) {
        int[] arr0 = {1, 2, 3};
        int[] arr1 = {4, 5, 6};
        arr0 = new int[]{0};
        arr1 = new int[]{0};
        ListNode l0 = LinkedListUtil.generateLinkedList(arr0);
        ListNode l1 = LinkedListUtil.generateLinkedList(arr1);
        Leet002 s = new Leet002();
        ListNode res = s.addTwoNumbers(l0, l1);
        LinkedListUtil.showList(res);
    }
}
