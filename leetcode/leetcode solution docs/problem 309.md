## 309. Best Time to Buy and Sell Stock with Cooldown
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:


```
prices = [1, 2, 3, 0, 2]
```
maxProfit = 3

```
transactions = [buy, sell, cooldown, buy, sell]
```

#### tips
这个题目是一个很典型的动态规划题目， 需要求最优的问题 而这个最最优的问题又依赖于最优的子问题。最优的子问题的个数是有限的

如果使用暴力解法应该去列举出所有的可能 每一天都有三种状态 
- 1持有 
- 2刚卖出 
- 3什么都没持有
- 

如果暴力解法 将会是 3^n的复杂度

但是要知道 如果当前的这一天是最后的一天， 那么如果要求这一天的最大收益 那么就只和前一天的结果是相关的 但是我们不知道 前一天处于哪一种状态下能够使得今天的收益最大， 所以 我们就应该把昨天三种状态下的最大收益都求出来。其实 前一天处于三种状态下可能的情形不止三种，但是我们为了利润最大 使得只取这三种情况分别取得最大利润的情况。 这样就相当于通过最优的条件筛选掉了很多 其他的情况，使得问题的复杂度大幅下降

我们可以画出一个状态图

![image](http://i.imgur.com/wvR4TN8.png?1)

```
s0[i] = max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
s2[i] = s1[i - 1] + prices[i]; // Only one way from s1
```
状态的转移如上式 上式使用的是当前的总资产这一原则。 只要保障最后一步的总资产最大即可 这种最优化的问题 中间的步骤不一定是最优的 但最后是最优的 可以粗浅的理解为 我们开了上帝视角以后 就可以进行长远的规划 从大局全局最优的情况下保证而不是用贪心进行鼠目寸光的投资 

#### mycode

```
class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if len(prices) <= 1:
            return 0
        # state s is the money i have now
        s0 = [None, ] * len(prices)  # have 1
        s1 = [None, ] * len(prices)  # sold One
        s2 = [None, ] * len(prices)  # cool Down
        s0[0] = 0
        s1[0] = -prices[0]
        s2[0] = -0xffffffff
        for i in range(1, len(prices)):
            s0[i] = max(s2[i - 1], s0[i - 1])
            s1[i] = max(s0[i - 1] - prices[i], s1[i - 1])
            s2[i] = s1[i - 1] + prices[i]
        return max(s0[len(prices) - 1], s2[len(prices) - 1])
```
