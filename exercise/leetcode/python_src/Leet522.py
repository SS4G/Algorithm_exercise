class Solution(object):
    def findLUSlength(self, strs):
        """
        :type strs: List[str]
        :rtype: int
        """

        if len(strs) == 0:
            return -1
        if len(strs) == 1:
            return len(strs[0])
        cntDict = {}
        for s in strs:
            cntDict.setdefault(s, 0)
            cntDict[s] += 1
        #maxLen = max([len(i) for i in strs])
        unique = [s for s in cntDict if cntDict[s] == 1]
        unique.sort(key=len, reverse=True)
        nonUnique = [s for s in cntDict if cntDict[s] > 1]
        nonUnique.sort(key=len, reverse=True)
        #print(unique)
        #print([self.isSubsequence(i, "cca") for i in nonUnique])

        if unique:
            for u in unique:
                abortFlag = False
                for n in nonUnique:
                    if len(n) >= len(u):
                        res = self.isSubsequence(n, u)
                        if res:
                            abortFlag = True
                            break
                    else:
                        break
                if not abortFlag:
                    return len(u)
            return -1
        else:
            return -1

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
    strs = ["abcd", "abce", "abcd", "abce", "abc", "dr"]
    strs = ["abcabc", "abcabc", "abcabc", "abc", "abc", "cca"]
    print(s.findLUSlength(strs))
    print(s.isSubsequence("abcabc", "cca"))

