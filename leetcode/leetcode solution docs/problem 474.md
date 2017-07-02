## 474. Ones and Zeroes
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:

```
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
```
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Example 2:

```
Input: Array = {"10", "0", "1"}, m = 1, n = 1
```
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

#### tips
也是一个典型的背包问题 0,1的数量相当于背包的容量 字符串的长度相当于背包内物品对应的重量， 所有最终容纳的字符串的数量相当于物品的价值

#### mycode

```
class Solution(object):
    def findMaxForm(self, strs, m, n):
        """
        :type strs: List[str]
        :type m: int
        :type n: int
        :rtype: int
        """
        arr = [None, ] * len(strs)
        for i in range(len(strs)):
            arr[i] = (strs[i].count("0"), strs[i].count("1"))
        dpRec = [[0, ] * (n + 1) for i in range(m + 1)]
        for s in arr:
            i = m
            while i >= s[0]:
                j = n
                while j >= s[1]:
                    dpRec[i][j] = max(dpRec[i][j], dpRec[i - s[0]][j - s[1]] + 1)
                    j -= 1
                i -= 1
        return dpRec[m][n]
```
