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
        
        
     
a=ListNode(0)        
b=ListNode(0)        
c=ListNode(0)        
d=ListNode(0)        
e=ListNode(0)   

a.next=b
b.next=a
c.next=a
d.next=e 
e.next=a

s=Solution()
print(s.hasCycle(a))
