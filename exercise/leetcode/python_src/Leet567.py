class Solution(object):
    def checkInclusion(self, s1, s2):
        """
        :type s1: str
        :type s2: str
        :rtype: bool
        """
        patternDict = {}
        for i in s1:
            if i not in patternDict:
                patternDict[i] = 1
            else:
                patternDict[i] += 1

        for j in range(len(s2) - len(s1) + 1):
            if j == 0:
                for i in range(len(s1)):
                    if s2[i] in patternDict:
                        patternDict[s2[i]] -= 1
            else:
                preIndex = j - 1
                if s2[preIndex] in patternDict:
                    patternDict[s2[preIndex]] += 1
                nextIndex = j + len(s1) - 1
                if s2[nextIndex] in patternDict:
                    patternDict[s2[nextIndex]] -= 1
            if self.isZero(patternDict, s1):
                return True
        return False

    def isZero(self, dict0, pattern):
        for i in pattern:
            if dict0[i] != 0:
                return False
        return True

if __name__ == "__main__":
    s = Solution()
    s1 = "adc"
    s2 = "dcda"
    print(s.checkInclusion(s1, s2))




