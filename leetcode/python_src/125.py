class Solution(object):
    def isPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        li=[]
        for i in s:
            if self.is_chap(i):
                li.append(self.to_low(i))
        length=len(li)
        length_tmp=length>>1
        for i in range(length_tmp):
            if li[i]!=li[length-1-i]:
                return False
            else:
                pass
        return True
        
    
    def is_chap(self,x):
        if x in "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ":
            return True
        else:
            return False
        return None
        
    def to_low(self,x):
        l="abcdefghijklmnopqrstuvwxyz"
        if x in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
            return l[ord(x)-ord('A')]
        else:
            return x
        
s=Solution()
print(s.isPalindrome("0P"))       
        
        
        
        