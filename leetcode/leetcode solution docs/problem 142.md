## 142. Linked List Cycle II Add to List

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

- Follow up:
- Can you solve it without using extra space?

#### tips
使用快慢指针来完成这个题目

一个快指针 一个慢指针
假设 
环是这样的：
a-b-c-d-e-f-g-c
环的结合点在c处
当slow指针到达环的结合点处时，已走过的路程为k fast 指针走过的路程为 2k
然后在环内， 两个指针相互追赶， 当fast赶上slow时， 又走过了r-k步，r为环的长度 此时两个指针在环上距离c点 为k处 所以最终剩下的路程为 r-(r-k) = k步 所以此时将一个指针退回到起点 然后 从起点开始再走这是两个指针的速度都保持一次一步 当 k步后两个指针再相遇的时候 就是这个环的进入点


#### mycode

```
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class LinkedListUtil:
    @staticmethod
    def merge2Ascendinglist(l1, l2):
        """
        合并两个升序的链表
        合并后仍然是一个升序的链表
        :param l1:
        :param l2:
        :return:
        """
        t1 = l1
        t2 = l2
        newHead = None
        tmp = None
        while (t1 is not None) and (t2 is not None):
            if newHead is None:
                if t1.val < t2.val:
                    newHead = t1
                    t1 = t1.next
                else:
                    newHead = t2
                    t2 = t2.next
                tmp = newHead
            else:
                if t1.val < t2.val:
                    tmp.next = t1
                    t1 = t1.next
                else:
                    tmp.next = t2
                    t2 = t2.next
                tmp = tmp.next
        if t1 is not None:
            tmp.next = t1
        if t2 is not None:
            tmp.next = t2
        return newHead

    @staticmethod
    def size(head):
        cnt = 0
        while head is not None:
            head = head.next
            cnt += 1
        return cnt

    @staticmethod
    def toPosition(head, pos):
        while pos > 0:
            head = head.next
            pos -= 1
        return head

    @staticmethod
    def link2Arr(head):
        output = []
        while head is not None:
            output.append(head.val)
            head = head.next
        # print(output)
        return output

    @staticmethod
    def showList(head):
        print("->".join([str(int0) for int0 in LinkedListUtil.link2Arr(head)]))

    @staticmethod
    def genList(l):
        head = None
        tmp = head
        for val in l:
            if head is None:
                head = ListNode(val)
                tmp = head
            else:
                tmp.next = ListNode(val)
                tmp = tmp.next
        return head


class Solution(object):
    def detectCycle(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None:
            return None
        try:
            fast = head.next.next
            slow = head.next
        except Exception:
            return None

        while fast is not slow:
            if (fast is not None) and (fast.next is not None):
                fast = fast.next.next
                slow = slow.next
            else:
                return None

        # must has cycle
        fast = head
        while fast is not slow:
            fast = fast.next
            slow = slow.next
        return slow
```
