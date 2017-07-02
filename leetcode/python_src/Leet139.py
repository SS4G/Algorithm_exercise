class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        if s == "":
            return False
        wordSet = set([])
        for i in wordDict:
            wordSet.add(i)
        dpRecord = [False, ] * (len(s) + 1)
        dpRecord[0] = True
        for i in range(len(s) + 1):
            for j in range(i):
                if dpRecord[j] is True and (s[j: i] in wordSet):
                    dpRecord[i] = True
                    break
        return dpRecord[len(s)]

if __name__ == "__main__":
    s = Solution()
    str0 = "leetcodecode"
    dicts = ["leet", "code"]
    assert s.wordBreak(str0, dicts) is True, "WA"
    str0 = "aaaaa"
    dicts = ["a", "aa"]
    assert s.wordBreak(str0, dicts) is True, "WA"