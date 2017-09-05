class Solution(object):
    def maxCount(self, m, n, ops):
        """
        :type m: int
        :type n: int
        :type ops: List[List[int]]
        :rtype: int
        """

        mina = m
        minb = n
        for op in ops:
            if op[0] < mina:
                mina = op[0]
            if op[1] < minb:
                minb = op[1]
        return mina*minb

if __name__ == "__main__":
    s = Solution()
    m = 3
    n = 3
    ops = []
    print(s.maxCount(m, n, ops))
