# leetcode 206
## Question
#### Reverse Linked List
Reverse a singly linked list.
## Answer
首先通过遍历整个链表，将所有节点的引用都存放在一个列表里
然后根据这个列表，对节点中的值进行交换
这种在列表中对链表进行操作的方法很重要

##### code
```Python
# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        tmp=head
        ptr_list=[]
        while tmp!=None:
            ptr_list.append(tmp)
            tmp=tmp.next        
        
        index=0
        length=len(ptr_list)
        end_index=(length>>1)-1
            
        while index<=end_index:
            ptr_list[index].val,ptr_list[-index-1].val=ptr_list[-index-1].val,ptr_list[index].val
            index+=1        
        return head
```
