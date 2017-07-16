## 567. Permutation in String

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:

```
Input:s1 = "ab" s2 = "eidbaooo"
```
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

```
Input:s1= "ab" s2 = "eidboaoo"
```
Output: False  
Note:
- The input strings only contain lower case letters.
- The length of both given strings is in range [1, 10,000].
- 


#### tips
大致的思想就是使用一个hashmap记录 pattern中的字符的数量 然后用一个滑动的窗口去扫描被查找的序列
并且将当前滑动窗口中的字符进行统计 与pattern的字典去比较 如果相等就说明匹配成功 为了提高效率 实际上只使用一个字典来记录pattern 然后每当在滑动窗口中新增一个字符 如果在字典中就减去 将刚刚滑动出去字符 进行一个加一操作 如果什么时候窗口·使得字典中的所有值为0 那么就说明匹配成功

复杂度O(m(n-m))

#### mycode

```
class Solution(object):
    def checkInclusion(self, s1, s2):
        """
        :type s1: str
        :type s2: str
        :rtype: bool
        """
        patternDict = {}
        for i in s1:
            if i not in patternDict:
                patternDict[i] = 1
            else:
                patternDict[i] += 1

        for j in range(len(s2) - len(s1) + 1):
            if j == 0:
                for i in range(len(s1)):
                    if s2[i] in patternDict:
                        patternDict[s2[i]] -= 1
            else:
                preIndex = j - 1
                if s2[preIndex] in patternDict:
                    patternDict[s2[preIndex]] += 1
                nextIndex = j + len(s1) - 1
                if s2[nextIndex] in patternDict:
                    patternDict[s2[nextIndex]] -= 1
            if self.isZero(patternDict, s1):
                return True
        return False

    def isZero(self, dict0, pattern):
        for i in pattern:
            if dict0[i] != 0:
                return False
        return True
```
