#Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        tmp=head
        checked_list=[]
        while tmp!=None:
            checked_list.append(tmp)
            if tmp.next in checked_list:
                return True
            tmp=tmp.next
        return False
        
        
     
a=ListNode(0)        
b=ListNode(0)        
c=ListNode(0)        
d=ListNode(0)        
e=ListNode(0)   

a.next=b
b.next=c
c.next=d
d.next=e 
e.next=None

s=Solution()
print(s.hasCycle(a))

     