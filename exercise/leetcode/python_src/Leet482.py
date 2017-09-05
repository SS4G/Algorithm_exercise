class Solution(object):
    def licenseKeyFormatting(self, S, K):
        """
        :type S: str
        :type K: int
        :rtype: str
        """
        res = []
        dashCnt = 0
        for i in range(len(S)):
            c = S[len(S) - 1 - i]
            if c != '-':
                if ord('a') <= ord(c) <= ord('z'):
                    c = chr(ord(c) - (ord('a') - ord('A')))
                res.append(c)
                dashCnt += 1
                if dashCnt == K:
                    dashCnt = 0
                    res.append('-')
        res.reverse()
        i = 0
        while res[i] == '-':
            i += 1
        return "".join(res[i:])

if __name__ == "__main__":
    S = S = "--aa--aa--aa--"
    s = Solution()
    print(s.licenseKeyFormatting(S, 2))

