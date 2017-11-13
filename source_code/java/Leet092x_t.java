package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

import java.util.List;

/**
 * Created by BUPT_SS4G on 2017/10/12.
 */

class Leet092x {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (n - m <= 0) {
            return head;
        }
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        ListNode[] splited = split(dummy, m, n);
        //LinkedListUtil.showList(splited[0], "sp0");
        //LinkedListUtil.showList(splited[1], "sp1");

        ListNode reverseDummy = new ListNode('1');
        reverseDummy.next = splited[0].next;
        ListNode[] reversedPart = reverseinPlace(reverseDummy, splited[1]);
        //LinkedListUtil.showList(reversedPart[0], "rep0");
        //LinkedListUtil.showList(reversedPart[1], "rep1");

        splited[0].next = reversedPart[0];
        //LinkedListUtil.showList(reversedPart[0]);
        if (reversedPart[1] != null) {
            reversedPart[1].next = splited[1];
        }
        return dummy.next;
    }

    public ListNode[] split(ListNode DummyHead, int m, int n) {
        ListNode prePtr = DummyHead;
        ListNode nextPtr = DummyHead;
        for (int i = 0; i < n - m; i++) {
            //System.out.println("pp");
            nextPtr = nextPtr.next;
        }
        for (int i = 0; i < m - 1; i++) {
            //System.out.println("tmpfile");
            nextPtr = nextPtr.next;
            prePtr = prePtr.next;
        }
        nextPtr = nextPtr.next.next;
        ListNode[] res = new ListNode[2];
        res[0] = prePtr;
        res[1] = nextPtr;
        return res;
    }

    public ListNode[] reverseinPlace(ListNode DummyHead, ListNode ed) {
        ListNode placePtr = DummyHead.next;
        ListNode reverseDummy = new ListNode(7);
        ListNode reversePtr = reverseDummy;
        while (placePtr != ed) {
            reversePtr = reverseDummy.next;
            reverseDummy.next = placePtr;
            placePtr = placePtr.next;
            reverseDummy.next.next = reversePtr;
        }
        ListNode[] res = new ListNode[2];
        res[0] = reverseDummy.next;
        res[1] = DummyHead.next;
        return res;
    }
}

public class Leet092x_t {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ListNode head = LinkedListUtil.generateLinkedList(arr);
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        Leet092x leet = new Leet092x();
        ListNode[] splited = leet.split(dummy, 2, 4);
        //LinkedListUtil.showList(splited[0]);
        //LinkedListUtil.showList(splited[1]);
        //LinkedListUtil.showList(leet.reverseinPlace(dummy));
        LinkedListUtil.showList(leet.reverseBetween(head, 2, 4));
        //LinkedListUtil.showList(dummy);
    }
}
