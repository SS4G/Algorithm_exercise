## 424. Longest Repeating Character Replacement
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:

```
s = "ABAB", k = 2
```

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:

```
s = "AABABBA", k = 1
```

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

#### tips

1使用一个滑动的窗口 以当前窗口中最多的元素为基准去填充其他元素只要还有剩余的填充料 就尽情的向右扩展窗口 如果扩展不动 就记录下当前这一最长的窗口 然后从右向左收缩窗口 这个窗口相当于扫过了以所有点为起点的最长窗口 最终的结果必然产生在这些窗口中

#### mycode

```
class Solution(object):
    def characterReplacement(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """
        if len(s) == 0:
            return 0

        charCntDict = {chr(ord('A') + i): 0 for i in range(26)}
        st = 0  # right bound
        ed = -1  # left bound
        res = []
        advance = True
        while st < len(s):
            if advance:
                charCntDict[s[st]] += 1
            newVoteMax = self.getMaxItem(charCntDict)

            if (st - ed) - charCntDict[newVoteMax] <= k:  # different chars < k
                res.append(st - ed)
                st += 1
                advance = True
            else:
                ed += 1
                charCntDict[s[ed]] -= 1
                advance = False
        return max(res)

    def getMaxItem(self, itra):
        maxVal = -0xffffffff
        maxChar = None
        for i in itra:
            if maxVal < itra[i]:
                maxChar = i
                maxVal = itra[i]
        return maxChar
```
