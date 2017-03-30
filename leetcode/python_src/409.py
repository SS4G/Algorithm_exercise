class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        letter_dict={}
        ori="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        for i in ori:
            letter_dict[i]=0
        #letter_dict is buileded up 
        for i in s:
            letter_dict[i]+=1
            
        all_even=True
        length=0
        for i in letter_dict:
            if letter_dict[i]&0x01:#odd
                all_even=False
                length+=letter_dict[i]-1
            else:
                length+=letter_dict[i]
        if all_even:
            return length 
        else:
            return length+1
                    
s=Solution()
print(s.longestPalindrome("abccccdd"))
          
            
        
        
        
        
        
        
        