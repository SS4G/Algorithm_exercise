class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        last_val=7012809
        start_flag=1
        tmp_node=head
        uni_val_head=head
        while tmp_node!=None:            
            if tmp_node.val!=last_val:
                if start_flag:
                   #print("first")
                   p=0#dummy  unused
                else :
                    uni_val_head.next=tmp_node
                uni_val_head=tmp_node 
                
            if tmp_node.next=None:
                uni_val_head.next=None    
            last_val=tmp_node.val
            tmp_node=tmp_node.next  
            start_flag=0
        return head
            