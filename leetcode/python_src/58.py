class Solution(object):
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        ls=s.split(' ')
        print(ls)
        lls=len(ls)
        i=lls-1
        while ls[i]=='':
            i-=1
            if i==-1:
                return 0          
        
        return len(ls[i])
s=Solution()
print(s.lengthOfLastWord("   "))