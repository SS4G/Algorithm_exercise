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
        first_flag=False
        while tmp!=None:
            if not first_flag:
                print("first")
                if tmp.next==None:
                    return tmp
                else:
                    a=tmp
                    b=tmp.next
                    if b.next==None:# c d not exist
                        tmp=None
                        a.next=None
                        b.next=a
                        head=b                        
                    elif b.next.next==None:# d not exist
                        tmp=b.next
                        c=b.next
                        a.next=c
                        b.next=a
                        head=b
                    else :# c d exist
                        tmp=b.next
                        c=b.next
                        d=b.next.next
                        a.next=d
                        b.next=a
                        head=b                       
                first_flag=True
            else:
                if tmp.next==None:#last node of link list
                    return head
                else:
                    a=tmp
                    b=tmp.next
                    if b.next==None:# c d not exist
                        tmp=None
                        a.next=None
                        b.next=a
                    elif b.next.next==None:# d not exist
                        tmp=b.next
                        c=b.next
                        a.next=c
                        b.next=a
                    else :# c d exist
                        tmp=b.next
                        c=b.next
                        d=b.next.next
                        a.next=d
                        b.next=a                
        return head    
            
        
s=Solution()
a=ListNode('a')
b=ListNode('b')
c=ListNode('c')
d=ListNode('d')
e=ListNode('e')
f=ListNode('f')
g=ListNode('g')

a.next=b
b.next=c
c.next=d
d.next=e
e.next=None
f.next=g
g.next=None

print("-----")
z=s.swapPairs(a)
print_link_list(z)

