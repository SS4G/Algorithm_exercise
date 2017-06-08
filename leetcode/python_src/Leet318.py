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