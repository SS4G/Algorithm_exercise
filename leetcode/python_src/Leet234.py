# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        li=[]        
        while head!=None:
            li.append(head.val)
            head=head.next
        li2[:]=li[:]
        li2.reverse()
        if li2==li:
            return True
        else:
            return False
            
        
        
s=Solution()
s.isPalindrome()
        
        
        
        