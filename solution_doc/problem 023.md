## 23. Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

#### tips
使用优先队列来管理队头
复杂度O(nlgk) n是所有节点的总和
添加了一个可比较的包装类来方便将节点添加到优先队列中


#### mycode

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


class Solution {
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
```
