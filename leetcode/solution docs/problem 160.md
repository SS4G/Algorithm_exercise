# leetcode 160
## Question
#### Intersection of Two Linked Lists
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

```
graph LR

    a1-->a2
    a2-->c1
    c1-->c2
    c2-->c3
    b1-->b2
    b2-->b3
    b3-->c1
```

begin to intersect at node c1.

## Answer
如果用比较笨的办法将遍历过的节点存放在列表中去查找
空间复杂度O(n) 时间复杂度O(n^2)

所以新的方法是这样的 分别遍历AB链表记录下他们的长度
算出长度差，长度较长的一方减去差值后 连个链表便一样长
这样两个链表同时遍历就知道是否有共同部分了

如图 A遍历 长度为 5
B 遍历长度为6 所以差值为1 B减去1 后
同时同一个速度遍历AB 相等即可 
##### code

```
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
```

