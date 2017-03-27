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
        tmp=head
        while tmp!=None:
            if tmp.next!=None:
                tmp.val,tmp.next.val=tmp.next.val,tmp.val
                tmp=tmp.next.next
            else:
                break
        return head
        
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

print_link_list(a)
print("-----")
s.swapPairs(a)
print_link_list(a)

                
            