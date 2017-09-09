# leetcode 290
## Question
#### Word Pattern
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
## Answer
 需要考虑的点
1. 模式字符串与 实际被比较字符串在长度上不相同 此点最容易被忽略 
   eg. pattern = "aaaa", str = "dog dog "
2. pattern 中同一个 值 对应被比较字符串中的不同值
   eg. pattern = "abba", str = "dog cat cat fish" should return  
   false.
3. pattern 中不同的值 对应被比较字符串中相同的字符串
   pattern = "abba", str = "dog dog dog dog" should return false.

考虑到上述情况，采用字典的方式来处理
   首先建立一个空的字典（排除上述第一种pattern 与 str 不相同的情况）
   
   然后同时遍历pattern 和 分隔后的str（使用split函数将str 
   分割为词汇列表）
   


##### code
```Python
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
            if k not in pattern_dict:#检查键值是否已经存在于字典中
                dict_value_list=pattern_dict.values()#获取当前字典的值 并组成列表
                if strlist[index] in dict_value_list:#如果被比较字符串的值出现在字典中而 
                              #对应的pattern字符没有出现 说明 存在一个值对应多个pattern字符的情况
                    return False#失败
                pattern_dict[k]=strlist[index]#build new pattern-word key-val
                #如果没有就将新的键值对加入字典
            else :
                if pattern_dict[k] != strlist[index]:#check if pattern-word key-val right  
                    return False#如果同样的pattern 对应了不同的值 说明失败
                    break
                
            index+=1
        return True
```

