## 322. Coin Change

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

```
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)
```

Example 2:

```
coins = [2], amount = 3
return -1.
```

Note:
You may assume that you have an infinite number of each kind of coin.

#### tips
一个最基本的dp问题  dp问题的本质就是 一个大的问题可以转化为几个小的问题 能够形成一个状态转移的过程
为了节省时间 我们可以吧递归中查询过的某些状态用一个全局数组记录下来 每次递归时 要先查询一下这个状态数组是否已经被填充 如果已经被填充 就直接返回 如果还没有过记录 就继续递归的进行计算 计算完成后 把全局记录中的对应项补上 以便后续再用

比如 要算 11 因为11 分解后的左后一个加数可以使 1,2,5 所以我们可以去查找 10， 9， 6 最少需要几个硬币 那么

```
f(11) = 1+min(f(11-1), f(11-2), f(11-5))
```


#### mycode
class Solution(object):
    def coinChange(self, coins, amount):
        """
        :type coins: List[int]
        :type amount: int
        :rtype: int
        """
        if amount == 0:
            return 0
        coins.sort()
        record = [-2, ]*(amount + 1)
        res = self.coinChangeRecursive(coins, amount, record)
        return res

    def coinChangeRecursive(self, coins, amount, record):
        if record[amount] != -2:
            return record[amount]
        else:
            minAmount = 0xffffffff
            for coin in coins:
                if amount - coin == 0:
                    record[amount] = 1
                    minAmount = -1
                    return 1
                elif amount - coin < 0:
                    break
                else:
                    lastAmount = self.coinChangeRecursive(coins, amount - coin, record)
                    if lastAmount != -1:
                        minAmount = min(lastAmount, minAmount)
                    else:
                        pass
            if minAmount == 0xffffffff:
                record[amount] = -1
                return -1
            else:
                record[amount] = minAmount + 1
                return minAmount + 1