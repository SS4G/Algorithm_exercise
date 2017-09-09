## 507. Perfect Number Add to List

We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.

```
Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14
```

Note: The input number n will not exceed 100,000,000. (1e8)


#### tips
使用成对的关系来减少运算

注意约数的分界点 
sqrt(n)
#### mycode
```Python
class Solution(object):
    def checkPerfectNumber(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num <=0:
            return False

        E = 0.0000001
        sums = 1
        sq_double = num**0.5
        complte_sqrt_flag = False
        if abs(sq_double - int(sq_double)) < E:
            complte_sqrt_flag = True
            sq = (int)(sq_double)
        elif abs(sq_double-(int(sq_double)+1)) < E:
            sq = (int)(sq_double)+1
            complte_sqrt_flag = True
        else:
            sq = (int)(sq_double)+1
        for i in range(2, sq):
            if (num % i == 0):
                sums += i
                sums += num / i

        sums += sq if complte_sqrt_flag else 0
        return True if sums == num else False
```
