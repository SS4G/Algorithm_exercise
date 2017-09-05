## 633. Sum of Square Numbers
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False

#### tips

注意不要使用O(n^2)的笨方法 确定一个 然后去查询一个 O(n)

#### mycode


```
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
```
