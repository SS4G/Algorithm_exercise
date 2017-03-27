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
            else:
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

s=Solution()
strs="["
print(strs,"is ",s.isValid(strs))  
strs="[({()()})]"          
print(strs,"is ",s.isValid(strs))
strs="[){}[]"            
print(strs,"is ",s.isValid(strs)) 
strs="()()()()"           
print(strs,"is ",s.isValid(strs))            
        
        
        
        