class Solution(object):
    def grayCode(self, n):
        """
        :type n: int
        :rtype: List[int]
        """
        resultLength = 2**n
        oneResult = [0, ]*n
        allResults = [0, ]*resultLength
        pattern = [0, 1, 1, 0]
        for i in range(resultLength):
            for bit in range(n):
                # perido = 1 << (2+bit)
                # patternPerido = 1 << bit
                # oneResult[bit] = pattern[(i % perido) // patternPerido]
                oneResult[bit] = pattern[(i & ((1 << (2+bit))-1)) >> bit]
            res = 0
            for p in range(n):
                res += (1 << p) if oneResult[p] else 0
            allResults[i] = res
        return allResults

if __name__ == "__main__":
    s =Solution()
    res = s.grayCode(5)
    print(res)
