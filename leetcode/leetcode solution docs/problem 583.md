## 583. Delete Operation for Two Strings
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:

```
Input: "sea", "eat"
```
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".


- Note:
- The length of given words won't exceed 500.
- Characters in given words can only be lower-case letters.
- 


#### tips
使用dp去求两个字符串的最长公共子序列

#### mycode


```
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        l = self.getLCS(word1, word2)
        return len(word1) - l + len(word2) - l

    def getLCS(self, s0, s1):
        dpRec = [[0] * (len(s1) + 1) for i in range(len(s0) + 1)]
        for i in range(len(s0) + 1):
            for j in range(len(s1) + 1):
                if i == 0 or j == 0:
                    dpRec[i][j] = 0
                elif s0[i - 1] == s1[j - 1]:
                    dpRec[i][j] = dpRec[i - 1][j - 1] + 1
                else:
                    dpRec[i][j] = max(dpRec[i - 1][j], dpRec[i][j - 1])
        return dpRec[len(s0)][len(s1)]
```

