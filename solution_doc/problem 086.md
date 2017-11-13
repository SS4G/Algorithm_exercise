## 86. Partition List
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,  
return 1->2->2->4->3->5. 

#### tips
这个没啥好说的用两个带有哑节点的 链表 一个记录小于x的 一个记录大于等于x的 
最后把两个链表合并就好

这个主要是考察哑节点的使用 这样可以不用专门去写逻辑判断链表是不是空的 使得代码更简洁

#### mycode

```
class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        lt = ListNode(0xffffffff)
        ge = ListNode(0xffffffff)

        ltPtr = lt
        gePtr = ge
        ptr = head
        while ptr is not None:
            if ptr.val < x:
                ltPtr.next = ListNode(ptr.val)
                ltPtr = ltPtr.next
            else:
                gePtr.next = ListNode(ptr.val)
                gePtr = gePtr.next
            ptr = ptr.next
        ltPtr.next = ge.next
        return lt.next
```
