# leetcode 172
## Question
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
## Answer
特傻逼的方法就不说了
主要是新的方法
末尾的0 主要是因为有因数10 
一个因数10 必然对应一个末尾0 
因数10 是因为 2和 5
由于整个阶乘中 2的数量远比5 多
所以只要求所有因数中5 的个数之和即可

所以如下
##### code 
Beats （100%）
```Python
class Solution(object):
    def trailingZeroes(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n<5:
            return 0
        tmp=n
        sum=0
        while tmp>0:
            tmp=tmp//5
            tmp=int(tmp)
            sum+=tmp
        return sum
```
