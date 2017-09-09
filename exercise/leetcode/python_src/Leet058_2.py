class Solution(object):
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        length=len(s)
        if length==0:
            return 0
            
        i=-1
        while s[i]==' ':
            i-=1
            if i<-length:
                return 0
        l=0
        while self.is_chap(s[i]):
            l+=1
            i-=1
            if i<-length:
                return l
        return l                
        
    def is_chap(self,x):
        print((x in "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"))
        return (x in "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz") 
        
s=Solution()
print(s.lengthOfLastWord("  a"))
print("  v    a  ".split())

#S sushant9 
#Reputation:  1
# return (len(s.split()[-1]) if len(s.split()) >= 1 else 0)