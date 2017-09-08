class Solution(object):
    def imageSmoother(self, M):
        """
        :type M: List[List[int]]
        :rtype: List[List[int]]
        """
        rLength = len(M)
        if rLength <= 0:
            return []
        cLength = len(M[0])

        res = [[0, ] * cLength for i in range(rLength)]
        for r in range(rLength):
            for c in range(cLength):
                res[r][c] = self.getAdj(M, r, c, rLength, cLength)
        return res

    def getAdj(self, M, r, c, rLength, cLength):
        rs = [r, ]
        if r > 0:
            rs.append(r - 1)
        if r < rLength - 1:
            rs.append(r + 1)

        cs = [c, ]
        if c > 0:
            cs.append(c - 1)
        if c < cLength - 1:
            cs.append(c + 1)
        res = []
        i = 0
        for r0 in rs:
            for c0 in cs:
                res.append(M[r0][c0])
                i += 1
        #print(rs, cs)
        #print(res)
        return sum(res) // i

if __name__ == "__main__":
    s = Solution()
    M = [[1,1,1],
 [1,0,1],
 [1,1,1]]
    c = s.imageSmoother(M)
    print(c)