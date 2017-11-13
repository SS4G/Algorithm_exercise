# leetcdoe 19
## Question
#### Remove Nth Node From End of List
Given a linked list, remove the nth node from the end of list and return its head.

For example：
```


   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
```

Note:
Given n will always be valid.
Try to do this in one pass.
## tips
--- update @ 2017-08-04

为了使用onepass的solution 应该使用 两个指针 一个fast 一个slow 使fast先走n步 然后slow和fast一起走直到fast到达链表末尾 此时slow为要删除的节点， 其实操作时应该是fast到达倒数第一个节点 此时slow是要删除的节点的前一个节点 更方便删除

此题目如果使用头结点dummy那么对于边界的处理将更加的方便

#### mycode

```
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        ListNode prev = dummy;
        for (int i = 0; i < n; i ++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            //prev = slow;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```


## Answer
主要还是要注意各种情况的分类 以及最特殊情况的处理
一般的考虑问题自然是考虑到最一般的情况 这个要删除的节点在链表的中间
然后第一个特殊情况是要考虑删除的节点是链表最后一个
然后要考虑删除的节点时链表第一个节点

最特殊的情况是链表只有一个节点 这样就需要单独的处理

为了加快处理，先整个遍历一次链表然后将链表各个节的引用存放到列表中可以将链表当做顺序表来处理
##### code
```
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        tmp=head
        node_list=[]
        while tmp!=None:
            node_list.append(tmp)
            tmp=tmp.next
        
        length=len(node_list)
        if length==1:#special situation handle
            return None
        
        if n ==1:#n th node is tail
            node_list[-2].next=None
            return head
        elif n==length: # n th node head
            head=node_list[1]
            return head
        else:# n th node is in middle of linklist
            node_list[-n-1].next=node_list[-n+1]
            return head

        return None
```
