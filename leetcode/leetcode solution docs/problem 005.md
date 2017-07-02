## 5. Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"

#### tips


##### update
这个问题的通用解法其实不要求是马拉车算法 可以使用动态规划 或者是中心扩展法 这两种方法的时间复杂度都是O(n^2) 前者的空间复杂度一般为o(n^2) 可以优化至O(n) 后者的空间复杂度是 O(1)


- 动态规划法   
任意选取两个点
P(i, j) = True if P(i, j) 是回文串 else False

地推公式如下
```
P(i, j) =
if (P(i+1, j-1)  and (S_i == S_j))
    True
else
    False
```

- 中心扩展法
首先对字符串按照马拉车的算发进行预处理 这样结果肯定是奇数
然后从头选取一个点作为中心 向两边扩展 这样最后得到的结果是O(n^2)

--------------------

使用马拉车(mancher)算法可以获得很好地效果
注意题目是说的是子串而不是子序列。 子串必须在原始串中不是分离的 而子序列可以 只要保证相对的书序是正确的即可

[马拉车算法介绍](http://www.cnblogs.com/grandyang/p/4475985.html)



#### mycodes


```
class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        # use Manacher Algorithm
        processed = self.preProcess(s)
        mx = 0
        id0 = 0
        resLongest = 0
        mid = -1
        mark = [1, ]*len(processed)
        for i in range(len(processed)):
            mark[i] = 1 if i > mx else min(mark[(id0 << 1) - i], mx - i + 1)
            while 0 <= i + mark[i] < len(processed) and 0 <= i - mark[i] < len(processed)\
                  and processed[i + mark[i]] == processed[i - mark[i]]:
                mark[i] += 1
            if i + (mark[i] - 1) > mx:
                id0 = i
                mx = i + (mark[i] - 1)
            if mark[i] > resLongest:
                resLongest = mark[i]
                mid = i

        return self.deProcesss(processed[mid - (mark[mid] - 1): mid + mark[mid]])

    def preProcess(self, s):
        res = []
        j = 0
        for i in range(len(s)*2 + 1):
            if i & 0x01 == 0:
                res.append('\0')
            else:
                res.append(s[j])
                j += 1
        return "".join(res)

    def deProcesss(self, s):
        return "".join([j for j in s if j != '\0'])


if __name__ == "__main__":
    s = Solution()
    print(s.longestPalindrome("xxabaa1abcdcbaswd"))
    print(s.longestPalindrome("abcba"))
```
