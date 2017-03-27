class Solution(object):
    def reverseVowels(self, s):
        """
        :type s: str
        :rtype: str
        """
        vowels="aeiouAEIOU"
        list_s=list(s)
        k_index=0
        vowels_chap=[]
        vowels_index=[]
        for k in list_s:
            if k in vowels:
                vowels_chap.append(k)
                vowels_index.append(k_index)                
            k_index+=1
        
        for k in vowels_index:
            list_s[k]=vowels_chap.pop()           
      
        return "".join(list_s)

s=Solution()
print(s.reverseVowels("hello"))
print(s.reverseVowels("leetcode"))
print(s.reverseVowels(" "))
print(s.reverseVowels("a."))
