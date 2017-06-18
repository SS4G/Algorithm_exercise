## 392. Is Subsequence Add to List

Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:

```
s = "abc", t = "ahbgdc"
```

Return true.

Example 2:

```
s = "axc", t = "ahbgdc"
```

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
#### tips
本人的思想是 构建一个字典， 字典中存放着每个字符在t出现的位置
根据s 的字符搜索字典 看当前字符是否在字典中 在字典中的话 查找t中该字符的位置是否大于 原来上一个字符在字典中出现的位置   查找位置可以使用二分查找加快速度

##### snother way
当然也可以使用两个指针的做法 s 一个指针 t 一个指针 参考后面的代码

#### mycode
```Python
class Solution(object):
    def isSubsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        tDict = {}
        for c in set(list(t)):
            tDict[c] = []
        i = 0
        for c in t:
            tDict[c].append(i)
            i += 1
        lastIndex = -1
        for c0 in s:
            if c0 in tDict:
                greaterIndex = self.find1StGreater(tDict[c0], lastIndex)
                if greaterIndex == -1:
                    return False
                else:
                    lastIndex = greaterIndex
            else:
                return False
        return True

    def find1StGreater(self, l, target):
        """
        在有序的列表l中查找第一个大于target的数字
        :param l:
        :param target:
        :return: 返回结果 找不到返回 -1
        """
        lo = 0
        hi = len(l)-1
        bFlag = False
        if l[-1] <= target:  # not found
            return -1
        elif l[0] > target:
            return l[0]
        while hi >= lo:
            mid = (hi + lo) >> 1
            if l[mid] > target:
                hi = mid - 1
            elif l[mid] == target:
                bFlag = True
                break
            else:
                lo = mid + 1
        if bFlag:
            if mid < len(l) - 1:
                return l[mid+1]
            else:
                return -1
        else:
            return l[lo]

if __name__ == "__main__":
    s = Solution()
    print(s.find1StGreater([1, 3, 4, 5, 7, 9, 11], 11))
    assert s.isSubsequence("abc", "ahsdjbsdjkdsfkc") is True, "WA"
    assert s.isSubsequence("abu", "ahsdjbsdjkdsfkc") is False, "WA"
    assert s.isSubsequence("abc", "ahsdjabcjkdsfkc") is True, "WA"
```

another way

```Java
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
    }
}
```

