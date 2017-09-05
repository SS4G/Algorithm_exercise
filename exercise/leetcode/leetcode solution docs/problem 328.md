## 328. Odd Even Linked List

Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:

```
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.
```

##### Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...
#### tips
链表的原地合并 空间复杂度都是O(1)

#### mycode

```
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def oddEvenList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        oddHead = None
        evenHead = None
        eventmp = None
        oddtmp = None
        x = 0
        tmp = head
        while tmp is not None:
            if oddHead is None and (x & 0x01 == 0):
                oddHead = tmp
                oddtmp = oddHead
            elif evenHead is None and (x & 0x01 != 0):
                evenHead = tmp
                eventmp = evenHead
            elif x & 0x01 == 0:
                oddtmp.next = tmp
                oddtmp = oddtmp.next
            elif x & 0x01 == 1:
                eventmp.next = tmp
                eventmp = eventmp.next
            else:
                assert False, "shouldn't be here"
            tmp = tmp.next
            x += 1
        if oddtmp is not None:
            oddtmp.next = evenHead
        if eventmp is not None:
            eventmp.next = None
        return oddHead
```
