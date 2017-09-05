class Solution(object):
    def characterReplacement(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """
        if len(s) == 0:
            return 0

        charCntDict = {chr(ord('A') + i): 0 for i in range(26)}
        st = 0  # right bound
        ed = -1  # left bound
        res = []
        advance = True
        while st < len(s):
            if advance:
                charCntDict[s[st]] += 1
            newVoteMax = self.getMaxItem(charCntDict)

            if (st - ed) - charCntDict[newVoteMax] <= k:  # different chars < k
                res.append(st - ed)
                st += 1
                advance = True
            else:
                ed += 1
                charCntDict[s[ed]] -= 1
                advance = False
        return max(res)

    def getMaxItem(self, itra):
        maxVal = -0xffffffff
        maxChar = None
        for i in itra:
            if maxVal < itra[i]:
                maxChar = i
                maxVal = itra[i]
        return maxChar



if __name__ == "__main__":
    s = Solution()
    str0 = "AADABB"
    k = 2
    print(s.characterReplacement(str0, k))





