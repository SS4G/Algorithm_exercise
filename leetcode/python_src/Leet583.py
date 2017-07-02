class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        l = self.getLCS(word1, word2)
        return len(word1) - l + len(word2) - l

    def getLCS(self, s0, s1):
        dpRec = [[0] * (len(s1) + 1) for i in range(len(s0) + 1)]
        for i in range(len(s0) + 1):
            for j in range(len(s1) + 1):
                if i == 0 or j == 0:
                    dpRec[i][j] = 0
                elif s0[i - 1] == s1[j - 1]:
                    dpRec[i][j] = dpRec[i - 1][j - 1] + 1
                else:
                    dpRec[i][j] = max(dpRec[i - 1][j], dpRec[i][j - 1])
        return dpRec[len(s0)][len(s1)]

if __name__ == "__main__":
    s = Solution()
    x = ""
    y = ""
    print(s.minDistance(x, y))
