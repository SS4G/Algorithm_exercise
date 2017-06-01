# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        Output: 7 -> 0 -> 8
        means 465+342=807
        
        """
        res=ListNode(0)#creat result node head
        link_list1=l1
        link_list2=l2
        l1_list=[]         
        l2_list=[]         
        while True :
            if link_list1!=None :
                l1_list.append(link_list1.val)
            if link_list1.next!=None:
                link_list1=link_list1.next
            else :
                break
        
        while True :
            if link_list2!=None :
                l2_list.append(link_list2.val)
            if link_list2.next!=None:
                link_list2=link_list2.next
            else :
                break
            
        if len(l2_list)>len(l1_list):
            res=self.add_list(l2,l1)
        elif len(l2_list)<len(l1_list):
            res=self.add_list(l1,l2)
        else :
            res=self.add_list(l1,l2)        
        return res
        
    def add_list(self,l1,l2):
        """in this method l1>=l2"""
        carry=0
        head=l1
        last_l1=None
        while l1!=None :
            if l2!=None :
                this_bit=l1.val+l2.val+carry
                last_l1=l1#save l1
                l1=l1.next
                l2=l2.next
            else :
                this_bit=l1.val+carry
                last_l1=l1#save l1
                l1=l1.next
            
            if this_bit>=10 :
                last_l1.val=this_bit-10
                carry=1
            else :
                last_l1.val=this_bit
                carry=0                
            
        if carry :
            last_l1.next=ListNode(1)
        
        return head
def print_link_list(link_list0):
    while link_list0!=None:
        #print("itering")
        print(link_list0.val)
        link_list0=link_list0.next    
    return 

def creat_link_list(src_list):#src_list is non-reversed
    
    src_list_reversed=[]
    src_list_reversed[:]=src_list[:]
    src_list_reversed.reverse()
    link_list_head=None
    
    tmp_list_node=None
    first_flag=0
    for k in src_list_reversed:
        if not first_flag :
            link_list_head=ListNode(k)
            tmp_list_node=link_list_head
        else :
            tmp_list_node.next=ListNode(k)
            tmp_list_node=tmp_list_node.next
        first_flag=1
    return link_list_head


        
s=Solution()
#list_src1=[1,2,3]
#list_src2=[4,5,6]
#list_src1=[1,9,9]
#list_src2=[1]
list_src1=[9,9,9]
list_src2=[1]

l1=creat_link_list(list_src1)        
l2=creat_link_list(list_src2) 
print_link_list(l1)                  
print_link_list(l2)       
he=s.addTwoNumbers(l1, l2)
print_link_list(he) 
    