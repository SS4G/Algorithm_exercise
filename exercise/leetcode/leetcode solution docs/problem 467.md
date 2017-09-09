## 467. Unique Substrings in Wraparound String

Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.

Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:

```
Input: "a"
Output: 1
```


Explanation: Only the substring "a" of string "a" is in the string s.
Example 2:

```
Input: "cac"
Output: 2
```

Explanation: There are two substrings "a", "c" of string "cac" in the string s.
Example 3:

```
Input: "zab"
Output: 6
```

Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.


#### tips
这个题目有点意思 首先要发现规律 对于不同的可能的子串可以把他们分类 按照结尾的字符 来分类这样就可以 看出如果两个字符串的结尾字符相同 那么 较长的字符串子串的集合 将会包含较短字符串的所有子串的集合 

因为按照题目的限制 既然要求字符串是 相连的 只要结尾字符确定 长度确定 那么一个字符串必然确定。这样 短的字符串本身就是长的字符串的子串

在统计子串时 为了防止重复 我们把所有的子串按照 结尾进行分类
整个的过程最终其实就是统计 以某个字符结尾的字符串 最长的长度
有了这个长度 如 abcde 产生的以e为结尾的子串的个数就是5 其实可以看出规律

以某个字符结尾的字符串 中 以该字符结尾的子串数量其实就是这个串的长度 所以 最后把所有字符对应的以该字符结尾的子串的长度求和 即是结果

#### mycode

```
class Solution(object):
    def findSubstringInWraproundString(self, p):
        """
        :type p: str
        :rtype: int
        """
        if len(p) == 0:
            return 0
        elif len(p) == 1:
            return 1
        curLen = 1
        rec = [0, ] * 26
        rec[ord(p[0]) - ord('a')] = 1
        for i in range(1, len(p)):
            if self.isadj(p[i - 1], p[i]):
                curLen += 1
            else:
                curLen = 1

            idx = ord(p[i]) - ord('a')
            rec[idx] = max(rec[idx], curLen)
        return sum(rec)

    def isadj(self, s0, s1):
        if ord(s1) - ord(s0) == 1:
            return True
        elif s0 == "z" and s1 == "a":
            return True
        else:
            return False
```











#### mycode




