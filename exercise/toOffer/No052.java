package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;
import java.util.*;

/**
 *
 * Created by G5501 on 2017/9/13.
 */

class Solution052 {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null)
            return null;
        ListNode fast = pHead;
        ListNode slow = pHead;
        if (hasCompleteLoop(pHead))
            return pHead;
        do {
            fast = fast.next;
            if (fast == null)
                return null;
            fast = fast.next;
            if (fast == null)
                return null;
            slow = slow.next;
        } while (fast != slow);
        slow = pHead;
        do {
            fast = fast.next;
            slow = slow.next;
        } while (fast != slow);
        return fast;
    }
    private boolean hasCompleteLoop(ListNode head) { //检查是否整体是一个环
        Set<ListNode> cycle = new HashSet<>();
        ListNode ptr = head;
        while (true) {
            if (cycle.contains(ptr)) {
                return ptr == head;
            }
            else {
                cycle.add(ptr);
            }
        }
    }
}

public class No052 {
    public static void main(String[] args) {
    }
}

