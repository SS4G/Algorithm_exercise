class Solution(object):
    def nthUglyNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        curSet = {1}
        res = -1
        for i in range(n):
            min0 = min(curSet)
            curSet.add(min0 * 2)
            curSet.add(min0 * 3)
            curSet.add(min0 * 5)
            res = min0
            curSet.remove(min0)
        return res

if __name__ == "__main__":
    s = Solution()
    res = s.nthUglyNumber(1680)
    print(res)