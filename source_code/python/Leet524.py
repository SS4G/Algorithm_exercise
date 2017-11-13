class Solution(object):
    def findLongestWord(self, s, d):
        """
        :type s: str
        :type d: List[str]
        :rtype: str
        """
        if not d:
            return ""
        if not s:
            return ""

        d.sort(key=self.sortHelper, reverse=True)
        for d0 in d:
            if self.isSubsequence(s, d0):
                return d0
        return ""

    def sortHelper(self, s):
        list0 = [len(s), ]
        for i in s:
            list0.append(-ord(i))
        return tuple(list0)

    def isSubsequence(self, s0, pat):
        if len(pat) > len(s0):
            return False
        j = 0
        for i in pat:
            while j < len(s0) and s0[j] != i:
                j += 1
            if j == len(s0):
                return False
            j += 1
        return True

if __name__ == "__main__":
    s = Solution()
    s0 = "axxbxxcxxd"
    d = ["cx", "bx", "ax"]
    print(s.findLongestWord(s0, d))