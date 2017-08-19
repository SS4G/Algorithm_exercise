class Solution:
    def minSteps(self, n):
        """
        :type n: int
        :rtype: int
        """
        map = {}
        map[1] = 0
        return self.getMinStep(n, map)

    def getMinStep(self, n, map):
        if n in map:
            return map[n]
        minStep = 0x7fffffff
        for i in range(1, (n >> 1) + 1):
            if n % i == 0:
                minStep = min(self.getMinStep(i, map) + (n // i), minStep)
        map[n] = minStep
        return map[n]

if __name__ == "__main__":
    s = Solution()
    print(s.minSteps(3))