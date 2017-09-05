## 50. Pow(x, n) 
Implement pow(x, n).
#### tips
用 二分的乘法求幂 难是不难 就是要注意很多特殊的情况 比如 指数为 -n -1 0 的边界情况都要考虑进去
#### mycode
```Python
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


        if abs(x - 0) < 0.0000000001:
            return 0.0
        if n == 1:
            return x if negFlag is False else 1/x
        if n == 0:
            return 1.0
        if n & 0x000000001 == 0:
            return self.myPow(x*x, n >> 1) if negFlag is False else 1/self.myPow(x*x, n >> 1)
        else:
            return self.myPow(x*x, n >> 1)*x if negFlag is False else 1/(self.myPow(x*x, n >> 1)*x)
```
