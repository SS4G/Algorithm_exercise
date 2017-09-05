#leetcode 225
## Question
#### Implement Stack using Queues
Implement the following operations of a stack using queues.


```
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
```


```
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
```

## Answer
使用两个队列来交替的完成弹出工作
使用一个交替变量来记录
##### code

```
class Stack(object):
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.que0=[]
        self.que1=[]
        self.stack_length=0
        self.use_que1=False  
        self.que1_len=0
        self.que0_len=0       
        
    def in_queue(self,que,x):
        que.append(x)        
        
    def out_queue(self,que):
        res=que[0]
        del(que[0])
        return res         

    def push(self, x):
        """
        :type x: int
        :rtype: nothing
        """
        if self.use_que1:
            self.in_queue(self.que1,x)
            self.que1_len+=1
            self.stack_length+=1
        else:
            self.in_queue(self.que0,x)
            self.que0_len+=1
            self.stack_length+=1
            
    def pop(self):
        """
        :rtype: nothing
        """
        if self.use_que1:
            for i in range(self.que1_len-1):
                self.in_queue(self.que0,self.out_queue(self.que1))                
            res=self.out_queue(self.que1)
            self.que0_len=self.que1_len-1
            self.que1_len=0
            self.use_que1=not self.use_que1     
            self.stack_length-=1            
        else:
            for i in range(self.que0_len-1):
                self.in_queue(self.que1,self.out_queue(self.que0))                
            res=self.out_queue(self.que0)
            self.que1_len=self.que0_len-1
            self.que0_len=0
            self.use_que1=not self.use_que1     
            self.stack_length-=1  
         
        return res
   
    def top(self):
        """
        :rtype: int
        """
        if self.use_que1:
            return self.que1[self.que1_len-1]
        else:
            return self.que0[self.que0_len-1]

    def empty(self):
        """
        :rtype: bool
        """
        return False if self.stack_length else True
```
