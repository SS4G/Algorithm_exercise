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
        
        
        
        
        
        
        
        
        