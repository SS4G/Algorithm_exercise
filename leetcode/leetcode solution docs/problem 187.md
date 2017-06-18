# 187. Repeated DNA Sequences

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,


```
Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
```


Return:

```
["AAAAACCCCC", "CCCCCAAAAA"].
```

#### tips
可以搞一个类似于指纹查找的算发， 也就是用一个长度固定的滑动窗口 用这个窗口的内容去算一个哈希值 然后建立一个字典， 逐个窗口的去计算看是否有在字典中出现如果有则说明有 重复序列
输出结果的时候注意用set 否则 一个序列出现三次会被输出两次 用set可以避免 这里的哈希是用python的字典来完成的

#### mycode

```
class Solution(object):
    def findRepeatedDnaSequences(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        dictS = {}
        output = set([])
        for i in range(len(s) - 10 + 1):
            k = s[i: i + 10]
            if k not in dictS:
                dictS[k] = 1
            else:
                output.add(k)
        return list(output)
```
