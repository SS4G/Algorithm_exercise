## Add Two Numbers II
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.


```
Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
```

#### tips
使用堆栈保存值 然后从尾部到头部将两个list合并，合并过程中注意进位标志的设置设置进位标志后 在最后一次要进行检查 防止出现 多出来的一个1 比如 5+5 时就会出现这种情况

```Python
#### mycode
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        l2_copy = l2
        l1_copy = l1
        l1list = []
        l2list = []
        while l1 is not None:
            l1list.append(l1.val)
            l1 = l1.next
        while l2 is not None:
            l2list.append(l2.val)
            l2 = l2.next
        index1 = len(l1list) - 1
        index2 = len(l2list) - 1
        carry = False
        head = None
        while index1 >= 0 and index2 >= 0:
            val = l1list[index1] + l2list[index2] + (1 if carry else 0)
            carry = True if val >= 10 else False
            tmp = ListNode((val % 10))
            tmp.next = head
            head = tmp
            index1 -= 1
            index2 -= 1

        while index2 >= 0:
            val = l2list[index2] + (1 if carry else 0)
            carry = True if val >= 10 else False
            tmp = ListNode((val % 10))
            tmp.next = head
            head = tmp
            index2 -= 1

        while index1 >= 0:
            val = l1list[index1] + (1 if carry else 0)
            carry = True if val >= 10 else False
            tmp = ListNode((val % 10))
            tmp.next = head
            head = tmp
            index1 -= 1

        if carry:
            tmp = ListNode(1)
            tmp.next = head
            head = tmp
        return head
```
