# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        tmp=head
        node_list=[]
        while tmp!=None:
            node_list.append(tmp)
            tmp=tmp.next
        
        length=len(node_list)
        if length==1:#special situation handle
            return None:
        
        if n ==1:#n th node is tail
            node_list[-2].next=None
            return head
        elif n==length: # n th node head
            head=node_list[1]
            return head
        else:# n th node is in middle of linklist
            node_list[-n-1].next=node_list[-n+1]
            return head

        return None
            