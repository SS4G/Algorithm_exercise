## 122. Best Time to Buy and Sell Stock II
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
#### tips
使用相对变化量来计算 
只有O(n)的复杂度 
##### mycode(beats 90%)

```
class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        profit = 0
        if len(prices) <= 0:
            return 0
        pre = prices[0]
        for i in prices:
            profit += i-pre if i-pre > 0 else 0
            pre = i
        return profit
```
