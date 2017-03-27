class Solution(object):
    def reverseVowels(self, s):
        """
        :type s: str
        :rtype: str
        """
        vol_list="aeiouAEIOU"
        list_s=list(s)
        length=len(list_s)
        i=length-1
        j=0
        res=""
        while i>j and i>=0 and j<=length-1:
            while (list_s[i] not in vol_list) and (i>j and i>=0 and j<=length-1):
                i-=1
            while (list_s[j] not in vol_list) and (i>j and i>=0 and j<=length-1):
                j+=1
            list_s[i],list_s[j]=list_s[j],list_s[i]
            i-=1
            j+=1
        
        return "".join(list_s)
            
            
#test bench            
s=Solution()
print(s.reverseVowels("hello"))
print(s.reverseVowels("leetcode"))
print(s.reverseVowels(" "))
print(s.reverseVowels("a."))            