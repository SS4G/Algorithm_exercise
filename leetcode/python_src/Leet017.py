class Solution(object):
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        charList = [" ", "*", "abc", "def",  "ghi",  "jkl",  "mno",  "pqrs", "tuv",  "wxyz", ]  # 0 ~ 9
        res = []
        lastRes = [[]]
        for d in digits:
            newRes = []
            d = int(d)
            for c in charList[d]:
                for lr in lastRes:
                    l = []
                    l[:] = lr[:]
                    l.append(c)
                    newRes.append(l)
            lastRes = newRes
        res = lastRes
        return ["".join(item) for item in res]

if __name__ == "__main__":
    s = Solution()
    print(s.letterCombinations("234"))
