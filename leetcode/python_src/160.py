# Definition for singly-linked list.
#class ListNode(object):
#    def __init__(self, x):
#        self.val = x
#        self.next = None

class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
    
        a_info=self.count_linklist_len(headA)
        b_info=self.count_linklist_len(headB)
        
        if a_info[0]!=b_info[0]:# 如果最后一个节点不是一个 那么直接判断为没有交集
            return None
        elif a_info[1]<b_info[1]:
            tmpB=self.goto_node(headB,b_info[1]-a_info[1])
            tmpA=headA
        elif a_info[1]>b_info[1]:
            tmpA=self.goto_node(headA,a_info[1]-b_info[1])
            tmpB=headB
        elif a_info[1]==b_info[1]:
            tmpA=headA
            tmpB=headB
        
        while tmpA!=None and tmpB!=None:
            if tmpA==tmpB :#包括 tmpA==None tmpB==None 这种没有交集的情况
                return tmpA #同样可以处理
            else :
                tmpA=tmpA.next
                tmpB=tmpB.next
                
        return None    
        
        


    
    def count_linklist_len(self,head):
        tmp=head
        length=0
        while tmp!=None:
            length+=1
            if tmp.next==None:
                tail=tmp
                return [tail,length]
            tmp=tmp.next
        return [None,0]
             
    def goto_node(self,head,distance):
        tmp=head
        for j in range(distance):
            tmp=tmp.next         
        return tmp
        
s=Solution()
a0=ListNode('a0')
a1=ListNode('a1')
a2=ListNode('a2')
a3=ListNode('a3')
b0=ListNode('b0')
b1=ListNode('b1')
b2=ListNode('b2')
b3=ListNode('b3')
c0=ListNode('c0')
c1=ListNode('c1')
c2=ListNode('c2')
c3=ListNode('c3')

a0.next=a1
a1.next=a2
a2.next=a3
a3.next=None
b0.next=b1
b1.next=b2
b2.next=b3
b3.next=None
c0.next=c1
c1.next=c2
c2.next=c3
c3.next=None


print("-----")
d=s.getIntersectionNode(a0,b0)
if d!=None:
    print(d.val)
else:
    print("NULL")
print(None==None)
        
        
        
        
        
        
        
        
