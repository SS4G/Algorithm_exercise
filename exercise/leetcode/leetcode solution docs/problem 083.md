# leetcode 83
## Question
#### Remove Duplicates from Sorted List 
Total Accepted: 133977
Total Submissions: 354900
Difficulty: Easy
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
## Answer
使用标记的方法来记录首个值出现的节点
使用val_last 和 val_present 比较的方法
比较简单 直接上代码
##### my code

```Python
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
                
            if tmp_node.next=None:#special situation at end of the link list
                uni_val_head.next=None    
            last_val=tmp_node.val
            tmp_node=tmp_node.next  
            start_flag=0
        return head
```
