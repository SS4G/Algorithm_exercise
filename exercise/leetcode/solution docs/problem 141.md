# leetcode 141
## Question
#### Linked List Cycle
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
## Answer
一般的想法是将遍历过的节点地址放在一个列表中 每次遍历新的节点时就去列表中查找 找到了就有环 否则没有

但更简洁的办法就是设置一快一慢两个指针，如果有环必然出现套圈现象
快的追上慢的 否则永远不会追上 这样也不用占用额外的空间

##### code
```Python
#Definition for singly-linked list.
#class ListNode(object):
#    def __init__(self, x):
#        self.val = x
#        self.next = None

class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        tmp=head
        first_ptr=head
        second_ptr=head
        if head==None:
            return False
        
        if head.next==None:
            return False
         
        first_ptr=head
        second_ptr=head.next
        
        while first_ptr!=None and second_ptr!=None:
            if first_ptr ==second_ptr:
                return True
            first_ptr=first_ptr.next
            if second_ptr.next!=None:            
                second_ptr=second_ptr.next.next
            else :
                return False
        return False
```
