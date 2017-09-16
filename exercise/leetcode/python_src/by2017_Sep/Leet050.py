class Solution(object):
    def myPow(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        negFlag = False
        if n < 0:
            n = -n
            negFlag = True


        if x - 0 < 0.0000000001:
            return 0.0
        if n == 1:
            return x
        if n == 0:
            return 1.0
        if n & 0x000000001 == 0:
            return self.myPow(x*x, n >> 1) if negFlag is False else 1/self.myPow(x*x, n >> 1)
        else:
            return self.myPow(x*x, n >> 1)*x if negFlag is False else 1/(self.myPow(x*x, n >> 1)*x)

if __name__ == "__main__":
    s =Solution()
    print(s.myPow(2.0, 0))
    print(s.myPow(2.0, 1))
    print(s.myPow(2.0, 3))
    print(s.myPow(2.0, 8))
    print(s.myPow(2.0, -3))