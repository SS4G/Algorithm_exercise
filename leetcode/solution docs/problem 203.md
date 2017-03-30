# leetcode 203
## Question
#### Remove Linked List Elements
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
## Answer 
综合考虑一下各种情况
比如要删除的点在头部这类特殊情况 只要是一定要逻辑清晰
争取吧代码一次写正确
##### code


```Python
# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
class Solution(object):
    def removeElements(self, head, val):
        """
        :type head: ListNode
        :type val: int
        :rtype: ListNode
        """
        tmp=head
        pre=head
        while tmp!=None:
            if tmp.val==val:
                if tmp==head:
                    head=tmp.next
                    tmp=head
                    pre=head
                else:
                    pre.next=tmp.next
                    tmp=tmp.next
            else:
                pre=tmp
                tmp=tmp.next           
        return head
```

        
