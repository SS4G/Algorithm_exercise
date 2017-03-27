# leetcode 24
## Question
#### Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
## Answer
有三种方法
- 1 最快的如果不考虑题目中的不允许值交换的那一条，
直接进行成对节点的值属性交换，这种方法又快又简单

##### code1

```Python
class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        tmp=head
        while tmp!=None:
            if tmp.next!=None:
                tmp.val,tmp.next.val=tmp.next.val,tmp.val
                tmp=tmp.next.next
            else:
                break
        return head
```


- 2 采用递归的方法（不用值交换）
 由于链表指针的成对交换会造成一些不便
 如果从头部开始交换
 假设原来的链表为
 a->b->c->d
 那么需要注意的是 第一次交换后
 a.next=d而不是c 否则 在第二次交换后 会导致链表成这个样子
 b->a->c<-d
 
 所以最好的方法就是使用递归 在交换ab之前 先将之后的序列交换完成
 这样d就排在了c的前面 直接让a.next指向上次调用交换函数的结果
 问题就是递归会很慢

##### code2 
```Python
class Solution(object):
    def swapPairs(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head==None:
            return None
        elif head.next==None:
            return head
        else:
            next_ptr=self.swapPairs(head.next.next)
            a=head
            b=head.next
            a.next=next_ptr
            b.next=a
            return b
```

- 3 最后的一种方法就是不用递归 但是交换时需要将a.next指向d 但这就需要判断后面是 c d 都存在还是 只有c一个节点
 
 所以这就需要对b节点后面的情况进行判断
 

```Python
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
```


