class Solution(object):
    def canConstruct(self, ransomNote, magazine):
        """
        :type ransomNote: str
        :type magazine: str
        :rtype: bool
        """
        ran=list(ransomNote)
        mag=list(magazine)
        
        try:
            for i in ran:
                #p=mag.index(i)
                mag.remove(i)            
        except ValueError:
            return False
        else :
            return True
 
s=Solution()
print(s.canConstruct("a","b"))
print(s.canConstruct("aa","ab"))
print(s.canConstruct("aa","aab"))
