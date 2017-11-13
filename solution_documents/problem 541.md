## 541. Reverse String II Add to List

Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.

```
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
```

#### tips
原地操作交换更快速 比切片要快
#### mycode
```Python
class Solution:
    def reverse_part(self, list0, start, end):
        if len(list0) == 0:
            return
        i = start
        j = end-1
        while i < j:
            list0[i], list0[j] = list0[j], list0[i]
            i += 1
            j -= 1

    def reverseStr(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        list0 = list(s)
        length = len(list0)
        i = 0
        segment = 2*k
        while length - i > 2*k:
            self.reverse_part(list0, i, i+k)
            i += segment
        if length - i >= k:
            self.reverse_part(list0, i, i + k)
        else:
            self.reverse_part(list0, i, length)
        return "".join(list0)
```
