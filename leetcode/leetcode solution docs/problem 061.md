## 61. Rotate List

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
#### tips
因为k 可能比链表长度还大 所以 可以先把链表结成一个环 同时统计链表的长度
然后在该断开的地方断开 具体断开的位置为length - (k % length) - 1 注意题目的有旋转和左旋转是不一样的 正好是反的 关于具体偏移量的问题画个图看一下
        
分为两步
#### mycode
```Python
class Solution(object):
    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        tmp = head
        if tmp is None:
            return None
        length = 1
        
        while tmp.next is not None:
            length += 1
            tmp = tmp.next
        tmp.next = head  # make lisk a circle
        offset = length - (k % length) - 1
        
        if offset == 0:
            head.next = None
        else:
            for i in range(offset):
                head = head.next
            newHead = head.next
            head.next = None
        return newHead
```
