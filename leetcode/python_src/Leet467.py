class Solution(object):
    def findSubstringInWraproundString(self, p):
        """
        :type p: str
        :rtype: int
        """
        if len(p) == 0:
            return 0
        elif len(p) == 1:
            return 1
        curLen = 1
        rec = [0, ] * 26
        rec[ord(p[0]) - ord('a')] = 1
        for i in range(1, len(p)):
            if self.isadj(p[i - 1], p[i]):
                curLen += 1
            else:
                curLen = 1

            idx = ord(p[i]) - ord('a')
            rec[idx] = max(rec[idx], curLen)
        return sum(rec)

    def isadj(self, s0, s1):
        if ord(s1) - ord(s0) == 1:
            return True
        elif s0 == "z" and s1 == "a":
            return True
        else:
            return False

if __name__ == "__main__":
    s = Solution()
    r = s.findSubstringInWraproundString("abcdabc")
    print(r)

