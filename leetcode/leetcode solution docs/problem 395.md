## 395. Longest Substring with At Least K Repeating Characters
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:

```
s = "aaabb", k = 3
```

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:

```
s = "ababbc", k = 2
```

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
#### tips
这个题目的应用主要是分治法 因为我们要求子串中所有字母至少出现k次

在开始的一串中 如果某个字符揣想那次数少于k 那么我们就要将 包含这些字符的串去掉 得到的结果其实就是被这些子串分隔的多个子串 结果只可能出现在这些子串中， 所以我们对这些子串递归的调用上述的过程即可


```
class Solution(object):
    def longestSubstring(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """
        if len(s) == 0:
            return 0

        return self.divideRecursive(s, k)

    def divideRecursive(self, s, k):
        cntDict = {}
        for i in s:
            cntDict.setdefault(i, 0)
            cntDict[i] += 1

        badIdx = [-1, ]
        flag = True
        for idx in range(len(s)):  # save the bad idx
            if cntDict[s[idx]] < k:
                badIdx.append(idx)
                flag = False
        if flag:
            return len(s)

        badIdx.append(len(s))
        newStrs = []
        for i in range(1, len(badIdx)):
            s0 = s[badIdx[i-1] + 1: badIdx[i]]
            if len(s0) != 0:
                newStrs.append(s0)

        maxLen = 0
        for s1 in newStrs:
            maxLen = max(maxLen, self.divideRecursive(s1, k))
        return maxLen
```
