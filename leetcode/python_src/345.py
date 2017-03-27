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
        
        vowels_chap.reverse()
        k_index=0
        j_index=0
        length = len(list_s)
        vol_len=len(vowels_index)
        while k_index<length:
            if vol_len>j_index:
                if k_index==vowels_index[j_index] :
                    list_s[k_index]=vowels_chap[j_index]
                    j_index+=1
            else :
                break
            k_index+=1
            
        result=""
              
        return result.join(list_s)
        
s=Solution()
print(s.reverseVowels("hello"))
print(s.reverseVowels("leetcode"))
print(s.reverseVowels(" "))
print(s.reverseVowels("a."))

        
        