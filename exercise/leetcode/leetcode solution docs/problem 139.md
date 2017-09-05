## 139. Word Break

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given

```
s = "leetcode",
dict = ["leet", "code"].
```


Return true because "leetcode" can be segmented as "leet code".

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.

#### tips
使用 dp 来切分 要求s(i) 即前i个字符是否能够切分 那么就要求看s(i-j) and [i:j] in wordDict

如果这两个问题成立 那么 s(i) 成立

#### mycode
```
class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        if s == "":
            return False
        wordSet = set([])
        for i in wordDict:
            wordSet.add(i)
        dpRecord = [False, ] * (len(s) + 1)
        dpRecord[0] = True
        for i in range(len(s) + 1):
            for j in range(i):
                if dpRecord[j] is True and (s[j: i] in wordSet):
                    dpRecord[i] = True
                    break
        return dpRecord[len(s)]
```
