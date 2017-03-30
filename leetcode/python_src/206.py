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
        
        
s=Solution()
a=ListNode(1) 
tmp_node=a
tmp_node.next=ListNode(2)      
tmp_node=tmp_node.next
tmp_node.next=ListNode(3)  
s.reverseList(a)

     
 