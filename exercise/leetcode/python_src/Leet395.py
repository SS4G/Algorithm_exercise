class Solution(object):
    def longestSubstring(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """
        if len(s) == 0:
            return 0

        return self.divideRecursive(s, k)

    def divideRecursive(self, s, k):
        cntDict = {}
        for i in s:
            cntDict.setdefault(i, 0)
            cntDict[i] += 1

        badIdx = [-1, ]
        flag = True
        for idx in range(len(s)):  # save the bad idx
            if cntDict[s[idx]] < k:
                badIdx.append(idx)
                flag = False
        if flag:
            return len(s)

        badIdx.append(len(s))
        newStrs = []
        for i in range(1, len(badIdx)):
            s0 = s[badIdx[i-1] + 1: badIdx[i]]
            if len(s0) != 0:
                newStrs.append(s0)

        maxLen = 0
        for s1 in newStrs:
            maxLen = max(maxLen, self.divideRecursive(s1, k))
        return maxLen


if __name__ == "__main__":
    s = Solution()
    s0 = "ababbc"
    k = 2
    print(s.longestSubstring(s0, k))