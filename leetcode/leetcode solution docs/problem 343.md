## 343. Integer Break

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.

#### tips
[dp或者数学推到](http://www.cnblogs.com/zywscq/p/5415303.html)  
[或者证明因数指引该有2和3](http://blog.csdn.net/liyuanbhu/article/details/51198124)

对于所有可以整除2或者3的的可以 可以用数学推到的方式来决定 使用几乘积最大

对于所有的数>3 除了1 以外 所有的 都可以分解为 2 和3的和 3尽可能多 但如果 x%3==1 那么就考虑将最后的4 = 1 + 3 拆分成 4= 2 + 2 这样乘积最大 

#### mycode
```
class Solution(object):
    def integerBreak(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n == 1:
            return 1
        if n == 2:
            return 1
        if n == 3:
            return 2

        if n % 3 == 0:
            return 3 ** (n // 3)
        elif n % 3 == 1:
            return (3 ** ((n - 4) // 3)) * 4
        elif n % 3 == 2:
            return (3 ** ((n - 2) // 3)) * 2

        return None
```
