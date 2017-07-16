## 3. Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

#### tips
用两个指针， 一前一后，来维护一个集合保证当前的集合中没有重复的字符 每当前面的指针发现了一个和当前集合中重复的字符时 就把刚刚没有重复数字的那个集合的长度记录下来， 然后把后面的指针向前移动，知道越过那个刚刚重复的字符 这期间要把越过的字符从对应的set中删除掉。

具体的情况见代码

#### mycode


```
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) == 0:
            return 0
        maxLen = 0
        ptr0 = 0
        ptr1 = 0
        uniqueSet = set([])
        while True:
            while ptr0 < len(s) and (s[ptr0] not in uniqueSet):
                uniqueSet.add(s[ptr0])
                ptr0 += 1
            if ptr0 == len(s):
                maxLen = max(ptr0 - ptr1, maxLen)
                break
            else:
                maxLen = max(ptr0 - ptr1, maxLen)
                while s[ptr1] != s[ptr0]:
                    uniqueSet.remove(s[ptr1])
                    ptr1 += 1
                ptr1 += 1
                ptr0 += 1
        return maxLen

if __name__ == "__main__":
    s = Solution()
    st = ""
    print(s.lengthOfLongestSubstring(st))
```
