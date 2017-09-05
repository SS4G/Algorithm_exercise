## 91. Decode Ways
DescriptionHintsSubmissionsSolutions
Discuss   Editorial Solution Pick One
A message containing letters from A-Z is being encoded to numbers using the following mapping:


```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.


#### tips
典型的动态规划题目 如果当前要解码 s(n)  那么就应该考虑 s(n - 1) 以及 s(n - 2)本质上是需要找到动态规划的地推公式
在考虑s(n - 2)的时候要注意最后的两个数字是否在[1,26]区间内 如果不在 那么企图靠s(n-2)来完成解码的想法是不可行的 还有一个需要注意的是会出现03 这样的组合 这样的解码方式不存在 应该返回为0

#### mycode


```
class Solution(object):
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) == 0:
            return 0

        record = [0, ] * len(s)  # include this idx
        for i in range(len(s)):
            if i == 0:
                if s[i] == '0':
                    return 0
                else:
                    record[0] = 1
            elif i == 1:
                if s[i] == '0':
                    record[i] = 1 if 1 <= int(s[i - 1]) <= 2 else 0
                else:
                    record[i] = 2 if 0 < int(s[i - 1: i + 1]) <= 26 else 1
            else:
                if s[i] == '0':
                    if 0 < int(s[i - 1]) <= 2:
                        record[i] = record[i - 2]
                    else:
                        record[i] = 0
                else:
                    thisAdd = record[i - 2] if s[i - 1] != '0' and 0 < int(s[i - 1: i + 1]) <= 26 else 0
                    record[i] = record[i - 1] + thisAdd
        #print(record)
        return record[len(s) - 1]
```
