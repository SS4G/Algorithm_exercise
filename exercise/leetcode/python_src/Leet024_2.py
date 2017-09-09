#Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
def print_link_list(head):
    tmp=head
    while tmp!=None:
        print(tmp.val)
        tmp=tmp.next
    return None
class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head==None:
            return None
        elif head.next==None:
            return head
        else:
            next_ptr=self.swapPairs(head.next.next)
            a=head
            b=head.next
            a.next=next_ptr
            b.next=a
            return b
        
s=Solution()
a=ListNode('a')
b=ListNode('b')
c=ListNode('c')
d=ListNode('d')
e=ListNode('e')

a.next=b
b.next=c
c.next=d
d.next=e
e.next=None

print("-----")
z=s.swapPairs(a)
print_link_list(z)
