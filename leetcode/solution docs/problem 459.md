## 459. Repeated Substring Pattern   

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

```
Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True
```


Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

##### mycode
```Python
class Solution(object):
    def repeatedSubstringPattern(self, str):
        """
        :type str: strs
        :rtype: bool
        """
        length=len(str)
        str0=list(str)
        pattern_len=1
        pattern=""
        true_flag=True
        for pattern_len in range(1,length//2+1):
            if length%pattern_len!=0:
                continue
            else:
                pattern=str0[:pattern_len]
                #print(pattern)
                pattern_repeat_time=length//pattern_len
                k=0
                true_flag=True
                while k<pattern_repeat_time:
                    if pattern==str0[k*pattern_len:(k+1)*pattern_len]:
                        pass
                    else :
                        true_flag=False
                    k+=1
            if true_flag:
                return True
        return False
```
##### code use regex

```Python
def repeatedSubstringPattern(self, str):
    """
    :type str: str
    :rtype: bool
    """
    import re
    return bool(re.match(r"^([a-z]+)\1+$", str))
```
##### code use multiplie of string

```Python
def repeatedSubstringPattern(self, s):
    n = len(s)
    d = 1
    while d * d <= n:
        if n % d == 0:
            for m in {d, n/d}:
                if m > 1 and m * s[:n/m] == s:
                    return True
        d += 1
    return False
```
