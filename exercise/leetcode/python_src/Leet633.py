class Solution(object):
    def judgeSquareSum(self, c):
        """
        :type c: int
        :rtype: bool
        """
        if c <= 3:
            return False

        upLim = int(c ** 0.5)
        if upLim ** 2 == c:
            return True
        #print(upLim)
        for i in range(1, upLim + 1):
            b2 = c - i ** 2
            if int(b2 ** 0.5) ** 2 == b2:
                return True
        return False

if __name__ == "__main__":
    s = Solution()
    r = s.judgeSquareSum(5)
    print(r)