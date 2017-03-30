# leetcode 345
## Question
#### Reverse Vowels of a String
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

## Answer
方法有两种，

方法一 是就地修改交换
该方法在一次的迭代过程中的步骤如下：
  定义两个下标i和j，i自增直到遇到元音字符，j自减直到遇到元音字符。
  结束循环的条件是i>=j.
  
##### code

```Python
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
```
方法二：
先对字符串扫描一遍将元音字母的下标记录在列表中
同时将下标对应的字符也记录在一个列表中
然后根据列表中的下标来 使用pop弹出
元音紫府列表中的内容，这样就实现了翻转

##### code

```
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
```

###### 最后说一下
本题开始提交了多次但都应为超时而没有通过
后来经试验发现，主要的时间瓶颈居然在 最后的将字符列表合并成字符串的过程中

导致超时的代码：

```
res=""
 for k in list_s:
    res+=k
return res
```

可能由于字符串相加涉及到过多重复的内存操作 导致耗费了·大量的时间

参考他人的代码后改为如下的操作 就没有这个问题了

```
return "".join(list_s)
```

Python 具题实现字符串的加减的原理尚不清楚 但以后和·并字符串用join


































