class Solution(object):
    def wordPattern(self, pattern, str):
        """
        :type pattern: str
        :type str: str
        :rtype: bool
        """
        pattern_dict={}
        strlist=str.split(' ')
        index=0
        
        if len(pattern) != len(strlist):#首先考虑pattern长度和输入字符串长度不匹配的请况 
            return False                #不相同一律False
        
        for k in pattern:
            if k not in pattern_dict:#检查键值是否已经存在于
                dict_value_list=pattern_dict.values()
                if strlist[index] in dict_value_list:#value found but key not found
                    return False
                pattern_dict[k]=strlist[index]#build new pattern-word key-val 
            else :
                if pattern_dict[k] != strlist[index]:#check if pattern-word key-val right  
                    return False
                    break
                
            index+=1
        return True
        

s=Solution()
pattern = "abba"
str0 = "dog cat cat dog" 
print(s.wordPattern(pattern, str0))    
pattern = "abba"
str0 = "dog cat cat fish"
print(s.wordPattern(pattern, str0)) 
pattern = "aaaa"
str0 = "dog cat cat dog" 
print(s.wordPattern(pattern, str0)) 
pattern = "abba"
str0 = "dog dog dog dog" 
print(s.wordPattern(pattern, str0)) 
pattern = "abbcd"
str0 = "dog pig pig duck kock" 
print(s.wordPattern(pattern, str0)) 
    

       