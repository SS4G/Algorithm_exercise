## 82. Remove Duplicates from Sorted List II
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,

```
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
```

#### tips
使用一个头结点来完成任务是个很好地选择，这样就不用专门为 头部节点做特殊情况的考虑
使用一个pre指针来指向对应的前一个不重复的节点 用tmp指针来判断当前节点是否是一个重复性的节点

需要注意对尾部节点的处理

#### mycode
```
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        dummy = ListNode(-0xffffffff)
        dummy.next = head
        pre = dummy
        tmp = head
        while tmp is not None:
            if tmp.next is None:
                break
            if tmp.next.val != tmp.val:
                pre = tmp
                tmp = tmp.next
            else:
                thisVal = tmp.val
                while tmp is not None and tmp.val == thisVal:
                    tmp = tmp.next

                if tmp is None:
                    pre.next = None
                    break
                else:
                    pre.next = tmp
        return dummy.next

if __name__ == "__main__":
    s = Solution()
    arr = []
    head = LinkedListUtil.genList(arr)
    newHead = s.deleteDuplicates(head)
    LinkedListUtil.showList(newHead)
```
