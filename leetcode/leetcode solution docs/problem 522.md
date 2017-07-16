## 522. Longest Uncommon Subsequence II

Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

Example 1:

```
Input: "aba", "cdc", "eae"
Output: 3
```

Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].

#### tips
还记得上一个题目
[Longest Uncommon Subsequence I](https://leetcode.com/problems/longest-uncommon-subsequence-i/#/description)

最长非公共子序列的其实就是 如果两个字符串长度不同 那么就是长的那个 如果长度相同 但是字符串不同 那么他们俩任意个 如果字符串相同那么就没有

这个也是 但是这个不是两者之间

所以 第一步先统计字典中只出现过一次的 字符串 看他们中最长的是否是字典中最长的 如果是就是他 否则 就要 写一个判断一个串是否是另一个串的子序列的函数 这个函数的方法是用两个指针去线性查找 具体不在赘述 看代码即可 复杂度是O(m) m是被查找对象的长度

对这些唯一的字符串进行一个排序 从长到短 同样长度的按照字典序排序(可以借助元组的字典排序性质来帮忙)

然后按照这个顺序去那些不唯一的串中去查找 直到找到结果

#### mycode

```
class Solution(object):
    def findLUSlength(self, strs):
        """
        :type strs: List[str]
        :rtype: int
        """

        if len(strs) == 0:
            return -1
        if len(strs) == 1:
            return len(strs[0])
        cntDict = {}
        for s in strs:
            cntDict.setdefault(s, 0)
            cntDict[s] += 1
        #maxLen = max([len(i) for i in strs])
        unique = [s for s in cntDict if cntDict[s] == 1]
        unique.sort(key=len, reverse=True)
        nonUnique = [s for s in cntDict if cntDict[s] > 1]
        nonUnique.sort(key=len, reverse=True)
        #print(unique)
        #print([self.isSubsequence(i, "cca") for i in nonUnique])

        if unique:
            for u in unique:
                abortFlag = False
                for n in nonUnique:
                    if len(n) >= len(u):
                        res = self.isSubsequence(n, u)
                        if res:
                            abortFlag = True
                            break
                    else:
                        break
                if not abortFlag:
                    return len(u)
            return -1
        else:
            return -1

    def isSubsequence(self, s0, pat):
        if len(pat) > len(s0):
            return False
        j = 0
        for i in pat:
            while j < len(s0) and s0[j] != i:
                j += 1
            if j == len(s0):
                return False
            j += 1
        return True
```













