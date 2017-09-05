# leetcode 234
## Question
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

## Answer
要实现O(n) time and O(1) space
就要找到链表的中点 然后翻转前半段链表 与后半段链表进行比较
找中点用的是快慢指针法
##### code

```
class Solution(object):
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        tmp=head
        while tmp:
            #print(tmp.val,id(tmp))
            tmp=tmp.next        
        
        slow=fast=head
        rev=head
        pre=None
        rev_ne=None  
        #要考虑到链表长度为0 和为1 时 对于后面的处理方法不适用
        if head ==None or head.next==None:
            return True
        
        while fast and fast.next:#注意逻辑上的短路原则 要先判断fast 不为None 然后才能判断fast.next
            fast=fast.next.next
            slow=slow.next
        
        #对长度为2的链表进行原地翻转    
        while True:# reverse link list in place
            rev_ne=rev.next
            rev.next=pre
            pre=rev
            rev=rev_ne     
            if rev==slow:
                rev=pre
                break
        
        tmp=pre
        while tmp:
            #print(tmp.val,id(tmp))
            tmp=tmp.next
        
        #根据链表中点位置不同来进行不同的处理
        if fast:#middle point is slow.next
            slow=slow.next
            return self.cmp_link(rev,slow)
        else:#middle point is slow
            return self.cmp_link(rev,slow)
        
    def cmp_link(self,head1,head2):#比较两个链表是否完全一样
        tmp1=head1
        tmp2=head2
        while tmp1:     
            if tmp2==None:
                return False
            if tmp1.val!=tmp2.val:
                return False
            tmp1=tmp1.next
            tmp2=tmp2.next
        
        if tmp2!=None:
            return False
        return True
```
