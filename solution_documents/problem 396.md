# leetcode 396
## Question
#### Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.
## Answer
比较简单 先找出最短的字符串的长度
然后挨个比较每个字符串的字母,只有全部字符串都有这个字母才将这个字母加入结果中 否则迭代结束
##### code

```
class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        
        str_num=len(strs)
        
        if str_num==0:
            return ""
        elif str_num==1:
            return strs[0]
        min=len(strs[0])      
        prefix=[]
        for i in strs:
            tmp_len=len(i)
            if tmp_len<min:
                min=tmp_len
         
        end_flag=False
        for k in range(min):#prefix length
            chap_tmp=strs[0][k]
            
            for i in range(1,str_num):#string number in strs
                if strs[i][k]!=chap_tmp:
                    end_flag=True#
                    break
                    
            if not end_flag:            
                prefix.append(chap_tmp)
            else:
                break
                
        return "".join(prefix)
```
