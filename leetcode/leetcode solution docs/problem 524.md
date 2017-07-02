## 524. Longest Word in Dictionary through Deleting

Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:

```
s = "abpcplea", d = ["ale","apple","monkey","plea"]
```

Output: 
"apple"
Example 2:
Input:

```
s = "abpcplea", d = ["a","b","c"]
```

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

#### tips
其实就是一个球子序列的问题 使用一个 两个指针的判断子序列的函数 把字典按照题目的要求去排个序 然后 按照字典中的顺序从中拿出单词去 判断是不是s的子序列

#### mycode

```
class Solution(object):
    def findLongestWord(self, s, d):
        """
        :type s: str
        :type d: List[str]
        :rtype: str
        """
        if not d:
            return ""
        if not s:
            return ""
        
        d.sort(key=self.sortHelper, reverse=True)
        for d0 in d:
            if self.isSubsequence(s, d0):
                return d0
        return ""

    def sortHelper(self, s):
        list0 = [len(s), ]
        for i in s:
            list0.append(-ord(i))
        return tuple(list0)

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
