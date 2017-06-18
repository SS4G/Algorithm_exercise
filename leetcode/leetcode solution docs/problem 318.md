## 318. Maximum Product of Word Lengths

Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

```
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
```
Return 16
The two words can be "abcw", "xtfn".

Example 2:

```
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
```
Return 4
The two words can be "ab", "cd".

Example 3:

```
Given ["a", "aa", "aaa", "aaaa"]
```
Return 0
No such pair of words.

#### tips
每个单词中出现的字母可以用位运算 来表示 两个单词 中出现的字母， 两个单词中是否有重复的字母可以用他们映射出来的整数去按相与一下就可以
然后按照暴力 解法来吧符合条件的单词长度相乘 最后选出最大者

#### mycode

```
class Solution(object):
    def maxProduct(self, words):
        """
        :type words: List[str]
        :rtype: int
        """
        wordFingerPrint = [self.mapWordTobits(w) for w in words]
        maxLen = 0
        for i in range(len(words)):
            for j in range(i+1, len(words)):
                if wordFingerPrint[i] & wordFingerPrint[j] == 0:
                    length = len(words[i]) * len(words[j])
                    maxLen = maxLen if length < maxLen else length

        return maxLen

    def mapWordTobits(self, word):
        res = 0
        for c in word:
            bitPos = ord(c) - ord('a')
            mask = 0x01 << bitPos
            res |= mask
        return res

if __name__ == "__main__":
    s = Solution()
    arr0 = ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
    arr1 = ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
    arr2 = ["a", "aa", "aaa"]
    print(s.maxProduct(arr1))
```
