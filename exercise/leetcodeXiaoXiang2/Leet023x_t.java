package AlgorithmTraining.exercise.leetcodeXiaoXiang2;
import AlgorithmTraining.G55Utils.Java.*;
import java.util.*;

import java.util.ArrayList;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
class HeadElement implements Comparable<HeadElement> {
    ListNode myHead = null;
    HeadElement(ListNode head) {
        myHead = head;
    }

    public int compareTo(HeadElement head) {
        return Integer.compare(myHead.val, head.myHead.val);
    }
}

class Leet023x {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        PriorityQueue<HeadElement> pq = new PriorityQueue<>();
        ListNode addPtr = dummy;
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(new HeadElement(head));
            }
        }

        while (pq.size() > 0) {
            HeadElement he = pq.poll();
            addPtr.next = he.myHead;
            addPtr = addPtr.next;
            if (he.myHead.next != null) {
                he.myHead = he.myHead.next;
                pq.offer(he);
            }
        }
        return dummy.next;
    }

}

public class Leet023x_t {
    public static void main(String[] args) {
        int[][] arrs = {
                {1, 2, 3, 4},
                {2, 3, 5, 6},
                {3, 7, 8, 9},
                {1, 2, 2, 2},
                {5,},
                null,
        };
        ListNode[] larr = new ListNode[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            larr[i] = LinkedListUtil.generateLinkedList(arrs[i]);
        }
        Leet023x leet = new Leet023x();
        ListNode finalList = leet.mergeKLists(larr);
        LinkedListUtil.showList(finalList);
    }
}
