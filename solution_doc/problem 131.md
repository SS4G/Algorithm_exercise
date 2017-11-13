## 131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return


```
[
  ["aa","b"],
  ["a","a","b"]
]
```

#### tips
使用动态规划 
对于s(i)
如果 
str[j:i+1] 是回文串
那么 这个可以把这个str[j:i+1] 和s(j - 1) 的之前的结果拼接起来 即可

#### mycode

```
class Solution(object):
    def partition(self, s):
        """
        :type s: str
        :rtype: List[List[str]]
        """
        if len(s) == 0:
            return [""]
        record = [None for i in range(len(s))]  # include
        record[0] = [[s[0]]]
        #print(record[0])
        for i in range(1, len(s)):
            tmpRes = []
            #print("befor ", i, record)
            for otherPalind in self.findlastPalindrome(s, i + 1):
                #print("i=", i, "o=", otherPalind)
                if otherPalind - 1 >= 0:
                    for j in record[otherPalind - 1]:
                        #print("j", j)
                        cp = j[:]
                        cp.append(s[otherPalind: i + 1])
                        #print("cp", cp)
                        tmpRes.append(cp)
                else:
                    tmpRes.append([s[: i+1]])
            record[i] = tmpRes
            #print("after", i, record)
        return record[i]

    def findlastPalindrome(self, s, ed):
        res = []
        for i in range(ed):
            if self.validPalindrome(s, i, ed):
                res.append(i)
        return res

    def validPalindrome(self, s, st, ed):
        i0 = st
        i1 = ed - 1
        while i0 < i1:
            if s[i0] == s[i1]:
                i0 += 1
                i1 -= 1
            else:
                return False
        return True
```
