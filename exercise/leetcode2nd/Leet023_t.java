package AlgorithmTraining.exercise.leetcode2nd;

import AlgorithmTraining.G55Utils.Java.*;

import java.util.*;

/**
 * Created by G5501 on 2017/9/19.
 */
class ListHead implements Comparable<ListHead> {
    ListNode node;
    int idx;
    ListHead(ListNode head, int idx) {
        node = head;
        this.idx = idx;
    }

    public int compareTo(ListHead other) {
        if (node.val > other.node.val) {
            return 1;
        }
        else if (node.val == other.node.val) {
            return 0;
        }
        else {
            return -1;
        }
    }
}

class Leet023 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListHead> pq = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                pq.offer(new ListHead(lists[i], i));
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode ptr = dummy;
        while (pq.size() > 0) {
            ListHead tmpHead = pq.poll();
            ptr.next = tmpHead.node;
            lists[tmpHead.idx] = tmpHead.node.next;
            ptr = ptr.next;
            if (lists[tmpHead.idx] != null) {
                tmpHead.node = lists[tmpHead.idx];
                pq.offer(tmpHead);
            }
        }
        return dummy.next;
    }
}

public class Leet023_t {
    public static void main(String[] args) {
        int[] arr0 = {0, 3, 6, 9, 12, 15};
        int[] arr1 = {1, 4, 7, 10, };
        int[] arr2 = {2, 5, 8, 11, 14, 17};
        ListNode[] lists = {LinkedListUtil.generateLinkedList(arr0),
                LinkedListUtil.generateLinkedList(arr1),
                LinkedListUtil.generateLinkedList(arr2),};
        Leet023 leet = new Leet023();
        ListNode head = leet.mergeKLists(lists);
        head = leet.mergeKLists(new ListNode[0]);
        LinkedListUtil.showList(head);
    }
}
