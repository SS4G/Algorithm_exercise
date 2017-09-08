# -*- coding:utf-8 -*-
class Stack:
    def __init__(self):
        self.stack = []

    def stackPush(self, v):
        self.stack.append(v)

    def stackPop(self):
        return self.stack.pop()

    def size(self):
        return len(self.stack)

class Solution:
    def __init__(self):
        self.stack0 = Stack()
        self.stack1 = Stack()

    def push(self, node):
        # write code here
        self.fifoPush(node)

    def pop(self):
        # return xx
        return self.fifoPop()

    def fifoPush(self, node):
        self.stack0.stackPush(node)

    def fifoPop(self):
        if self.stack1.size() > 0:
            return self.stack1.stackPop()
        else:
            while self.stack0.size() > 0:
                self.stack1.stackPush(self.stack0.stackPop())
            return self.stack1.stackPop()

if __name__ == "__main__":
    s = Solution()
    a = [1, 2, 3, 4, 5]
    for i in  a:
        s.push(i)
    for i in range(3):
        print(s.pop())
    b = [6, 7, 8, 9]
    for k in b:
        s.push(k)
    for i in range(6):
        print(s.pop())

