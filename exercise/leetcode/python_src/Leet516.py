class Solution(object):
    def longestPalindromeSubseq(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) == 0:
            return 0
        s0 = s
        dpRec = [[0] * len(s0) for i in range(len(s0) + 1)]
        for i in range(len(s0)):
            dpRec[i][i] = 1
        for i in range(len(s0)):
            i0 = len(s0) - 1 - i
            for j in range(i0 + 1, len(s0)):
                if s0[i0] == s0[j]:
                    dpRec[i0][j] = dpRec[i0 + 1][j - 1] + 2
                else:
                    dpRec[i0][j] = max(dpRec[i0 + 1][j], dpRec[i0][j - 1])
        return dpRec[0][len(s0) - 1]

if __name__ == "__main__":
    s = Solution()
    # s0 = "aba"
    s0 = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa"
    print(s.longestPalindromeSubseq(s0))