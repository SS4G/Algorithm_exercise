# leetcode 20
## Question
#### Valid Parentheses  
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
## Answer
就是典型的括号匹配问题 使用堆栈来对括号进行匹配
如果是左括号就压入堆栈
如果是右括号，要查看堆栈中最后一个是否是匹配的左括号
若是 就弹出原有的左括号，否则 说明出现了错误
如果全部匹配成功 则最后遍历完整个字符串后 堆栈将是空的
##### code

```Python
class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        #test case list
        #括号配对成功即可 没有嵌套顺序的要求
        #"[()]" True
        #"([])" True
        #"[({()()})]" True
        #"()()()()"   True
        #"[){}[]"     False
        stack=[]
        length=len(s)
        
        if length&0x01!=0:#length error
            return False
        
        for i in range(length):
            if stack==[]:
                stack.append(s[i])
            elif stack[-1] in "([{":
                if self.is_cop(stack[-1],s[i]):
                    stack.pop()
                else:#may be new parentheses set begin
                    stack.append(s[i])
            else:#堆栈最后一个元素是右括号 肯定是错的
                return False
        
        if stack==[]:
            return True
        else:
            return False
        
    def is_cop(self,front,behind):
        dic={"(":")","{":"}","[":"]"}
        if behind==dic[front]:
            return True
        else :
            return False
```
