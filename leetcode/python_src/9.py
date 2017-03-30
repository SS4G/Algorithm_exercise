class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """        
        #valid test case
        #123321 true
        #-1     false
        #1      true
        #0      true
        #-0     false
        if x<0:
            return False         
        h=1
        p=int(x//h)
        x=int(x)
        while p>=10:
            h*=10
            p=int(x//h)            
            
        l=int(x//h)
        r=x%10
        while x>0:
            if l!=r:
                return False
            else:   
                x=x%h
                h=int(h//100)
                x=int(x//10)
                if h==0 :
                    break
                l=int(x//h)
                r=x%10        
        return True          
s=Solution()
print(s.isPalindrome(123321)) 
print(s.isPalindrome(123421)) 
print(s.isPalindrome(12321)) 
print(s.isPalindrome(-12321)) 
print(s.isPalindrome(-0)) 
print(s.isPalindrome(-1)) 
   
   
   
   
   
   
   
   
