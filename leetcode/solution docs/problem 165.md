# leetcode 165
## Question
#### Compare Version Numbers
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
## Answer
使用split函数将版本号 分隔转化成整型数组 然后再根据版本号的条件逐次比较
重点在于需要将版本号的大小情况考虑周全

只列出比较特殊的情况
v1=1.0
v2=1
虽然v1 长度长但是与v2 是一个版本
所以需要借助sum函数来判断最后的结果 即 共同长度之后的部分是否是有效的

如 v1=1.0.0.0
   后面的  .0.0.0实际上是无效的 用sum函数对后面进行相加如果的得到0
说明后面的号码无效
##### code 
```
class Solution(object):
    def compareVersion(self, version1, version2):
        """
        :type version1: str
        :type version2: str
        :rtype: int
        """
        list_str_ver1=version1.split('.')
        list_str_ver2=version2.split('.')        
        list_int_ver1=[]
        list_int_ver2=[]
        for k in list_str_ver1:
            list_int_ver1.append(int(k))
        for k in list_str_ver2:
            list_int_ver2.append(int(k))            
            
        l1=len(list_int_ver1)
        l2=len(list_int_ver2)
        min_len=l1 if l1<=l2 else l2
        #判断共同部分是否全部相同 若有不同 立即返回结果否则进行后续部分的判断
        for i in range(min_len):
            if list_int_ver1[i]<list_int_ver2[i]:
                return -1
            elif list_int_ver1[i]>list_int_ver2[i]:
                return 1
        
        if l1==l2:#l1==l2==min_len 两个版本号的共同长度部分相同 无后续部分 故两者完全相同
            return 0
        elif l1>l2:# l1 长度更大
            if sum(list_int_ver1[i+1:])>0: #l1 更多的长度部分有效 
                return 1
            else :#l1 更多的长度部分全为0 说明无效
                return 0
        else :#同上
            if sum(list_int_ver2[i+1:])>0: 
                return -1
            else :
                return 0
```
