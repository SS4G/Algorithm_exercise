class Solution(object):
    """
    my first dp code
    """
    def __init__(self):
        self.dpstate = [0, 1, 2, 3, ] + ([-1, ] * 10000)

    def numSquares(self, n):
        """
        :type n: int
        :rtype: int
        """
        res = self.dpRecursive(n, self.dpstate)
        return res

    def dpRecursive(self, n, stateRecord):
        if stateRecord[n] != -1:
            return stateRecord[n]
        else:
            maxSqrt = int(n**0.5)
            min = 0xffffffff
            while maxSqrt >= 1:
                tmp = self.dpRecursive(n - maxSqrt**2, stateRecord)
                min = tmp if tmp < min else min
                maxSqrt -= 1
            stateRecord[n] = min + 1
            return min + 1

if __name__ == "__main__":
    s = Solution()
    print(s.numSquares(6405))