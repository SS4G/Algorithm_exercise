# leetcode 232
## Question
#### Implement Queue using Stacks
mplement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
## Answer
使用堆栈的标准操作来实现 使用两个堆栈来实现
一个作为临时堆栈 另一个作为队列堆栈使用
每次获得队列的头部都要讲堆栈中后面的部分弹出到另一个堆栈中2保存起来 获取头部后 再将临时的堆栈压如原先的堆栈
##### code
```
#Implement the following operations of a queue using stacks.
#push(x) -- Push element x to the back of queue.
#pop() -- Removes the element from in front of queue.
#peek() -- Get the front element.
#empty() -- Return whether the queue is empty.

class Queue(object):
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.stack_list_tmp=[]
        self.stack_list1=[]
        self.now_storage=False#indicate which stack list is in use 
                          #stack_list0 and stack_list1 is alternating
        self.queue_len=0       

    def push(self, x):
        """
        :type x: int
        :rtype: nothing
        """        
        self.stack_list1.append(x)
        self.queue_len+=1
        return None

    def pop(self):
        """
        :rtype: nothing
        """
        for i in range(self.queue_len-1):
            self.stack_list_tmp.append(self.stack_list1.pop())
        res=self.stack_list1.pop()   
        self.queue_len-=1
        for i in range(self.queue_len):
            self.stack_list1.append(self.stack_list_tmp.pop())
        return res
        

    def peek(self):
        """
        :rtype: int
        """
        return  self.stack_list1[0]


    def empty(self):
        """
        :rtype: bool
        """
        if self.queue_len==0:
            return True
        else :
            return False 
        
```

        
        
        
        