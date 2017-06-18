## 143. Reorder List

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
#### tips
使用数组吧链表的节点对象保存起来 然后合并是个好方法
如果题目没有特殊要求的话  
#### mycode
```
class Solution(object):
    def reorderList(self, head):
        """
        :type head: ListNode
        :rtype: void Do not return anything, modify head in-place instead.
        """
        preSlow = head
        if head is None:  # 0node
            return None
        slowPtr = head.next
        if slowPtr is None:  # 1node
            return None
        fastptr = head.next.next
        if fastptr is None:  # 2node
            return None

        frontPart = []
        frontPart.append(head)
        while fastptr is not None and fastptr.next is not None:
            frontPart.append(slowPtr)
            fastptr = fastptr.next.next
            preSlow = slowPtr
            slowPtr = slowPtr.next

        preSlow.next = None
        behindPart = []
        while slowPtr is not None:
            behindPart.append(slowPtr)
            slowPtr = slowPtr.next
        behindPart.reverse()

        for i in range(len(frontPart)-1):
            frontPart[i].next = behindPart[i]
            behindPart[i].next = frontPart[i+1]
        frontPart[len(frontPart)-1].next = behindPart[len(frontPart)-1]
        if len(behindPart) == len(frontPart):
            behindPart[len(frontPart) - 1].next = None
        else:
            behindPart[len(frontPart) - 1].next = behindPart[len(frontPart)]
            behindPart[len(frontPart)].next = None
        return
```
