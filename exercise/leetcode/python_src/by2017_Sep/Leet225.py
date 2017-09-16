class Stack(object):
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.que0=[]
        self.que1=[]
        self.stack_length=0
        self.use_que1=False  
        self.que1_len
        self.que2_len        
        
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
        if use_que1:
            self.in_queue(self.que1,x)
            que1_len+=1
            stack_length+=1
        else:
            self.in_queue(self.que0,x)
            que0_len+=1
            stack_length+=1
            
    def pop(self):
        """
        :rtype: nothing
        """
        if use_que1:
            for i in range(que1_len-1):
                in_queue(que0,self.out_queue(que1))                
            res=self.out_queue(que1)
            que0_len=que1_len-1
            que1_len=0
            use_que1=not use_que1     
            stack_length-=1            
        else:
            for i in range(que0_len-1):
                in_queue(que1,self.out_queue(que0))                
            res=self.out_queue(que0)
            que1_len=que0_len-1
            que0_len=0
            use_que1=not use_que1     
            stack_length-=1  
         
        return res
   
    def top(self):
        """
        :rtype: int
        """
        if use_que1:
            return self.que1[que1_len-1]
        else:
            return self.que0[que0_len-1]

    def empty(self):
        """
        :rtype: bool
        """
        return False if stack_length else True
        
        
