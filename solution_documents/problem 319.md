## 319. Bulb Switcher Add to List

There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

```
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].
```

So you should return 1, because there is only one bulb is on.
#### tips
也就是求 每个灯泡对应位置 小于n的约数的个数
除非是完全平方数 约数的个数都是偶数 其结果必然是off 所以问题转化为求 小于n的 完全平方数

用sqrt(n) 即可
#### mycode

```Python
class Solution(object):
    def bulbSwitch(self, n):
        """
        :type n: int
        :rtype: int
        """
        return int(n**0.5)

if __name__ == "__main__":
    s = Solution()
    print(s.bulbSwitch(100))
```
