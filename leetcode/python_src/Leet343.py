class Solution(object):
    def integerBreak(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n == 1:
            return 1
        if n == 2:
            return 2
        if n == 3:
            return 3

        if n % 3 == 0:
            return 3 ** (n // 3)
        elif n % 3 == 1:
            return (3 ** ((n - 4) // 3)) * 4
        elif n % 3 == 2:
            return (3 ** ((n - 2) // 3)) * 2

        return None

if __name__ == "__main__":
    s = Solution()
    print(s.integerBreak(15))