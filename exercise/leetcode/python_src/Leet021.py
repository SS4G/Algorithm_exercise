# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def creat_list(self,head):
        res_list=[]
        while head!=None:
            res_list.append(head.val)
            head=head.next
        
        return res_list     
     
     
    def creat_link_list(self,src_list):#src_list is non-reversed
        src_list_reversed=[]
        src_list_reversed[:]=src_list[:]
 
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
        
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
    
        list1=self.creat_list(l1)
        list2=self.creat_list(l2)
        
        list2.extend(list1)
        list2.sort()
        
        return self.creat_link_list(list2)
    

    
