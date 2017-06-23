class Solution(object):
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        if m == 1 or n == 1:
            return 1
        elif m == 0 or n == 0:
            return 0
        rec = [[-1 for j in range(n)] for i in range(m)]
        for i in range(m):
            rec[i][0] = 1
        for j in range(n):
            rec[0][j] = 1
        for i in range(1, m):
            for j in range(1, n):
                rec[i][j] = rec[i][j - 1] + rec[i - 1][j]
        return rec[m - 1][n - 1]

if __name__ == "__main__":
    s = Solution()
    m = 3
    n = 7
    print(s.uniquePaths(m, n))