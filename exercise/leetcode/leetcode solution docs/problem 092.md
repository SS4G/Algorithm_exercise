## 92. Reverse Linked List II

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
- Given m, n satisfy the following condition:
- 1 ≤ m ≤ n ≤ length of list.


#### tips
使用一种一边转一遍插入的方法
举个例子旋转 [2,6]  
0:  1,2,3,4,5,6,7  
1:  1,3,2,4,5,6,7  
2:  1,4,3,2,5,6,7
...
看懂了吧 

如果一个链表中的位置太多哪一命名 就画个草图 标记个 n0，n1,n2 之类的标签


#### mycode
```
class Solution(object):
    def reverseBetween(self, head, m, n):
        """
        :type head: ListNode
        :type m: int
        :type n: int
        :rtype: ListNode
        """
        # 使用循环一部分链表去做
        dummyHead = ListNode(-1)
        dummyHead.next = head
        st = 0
        tmp = dummyHead
        while st < m:
            if st == m - 1:
                m_1Node = tmp
            st += 1
            tmp = tmp.next
        insertPtr = st
        n0 = m_1Node
        n1 = tmp
        n2 = tmp
        n3 = tmp.next
        while insertPtr < n:
            n4 = n3.next
            n0.next = n3
            n3.next = n1
            n2.next = n4

            # update ptrs
            n1 = n3
            n3 = n4
            insertPtr += 1
        return dummyHead.next
```
