## 279. Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

#### tips
一个典型的dp问题
每一个数字 分解为完全平方数后 
如果把这些完全平方数按照从大到小排列 最后一个数字一定是一个小于该数字的完全平方数 
如 13 分解为完全平方数后 器最后一个加数 只能为 9,4,1
最终不管怎样分解为完全平方数 倒数第二个状态只能为 13-9 13-4 13-1 三个 最少的的数字完全取决于这三个状态 所以符合dp的条件 可以用dp来解决

为了减少重复的运算次数 可以在创建Solution对象时 就创建一个数组，每次调用具体的方法时， 可以先去查找数组 看是否已经有了先前的结果 如果有就直接使用， 如果没有就用递归去计算 计算出来就填充到这个查询数组对应的位置

#### mycode

```
class Solution(object):
    """
    my first dp code
    """
    def __init__(self):
        self.dpstate = [0, 1, 2, 3, ] + ([-1, ] * 10000)

    def numSquares(self, n):
        """
        :type n: int
        :rtype: int
        """
        res = self.dpRecursive(n, self.dpstate)
        return res

    def dpRecursive(self, n, stateRecord):
        if stateRecord[n] != -1:
            return stateRecord[n]
        else:
            maxSqrt = int(n**0.5)
            min = 0xffffffff
            while maxSqrt >= 1:
                tmp = self.dpRecursive(n - maxSqrt**2, stateRecord)
                min = tmp if tmp < min else min
                maxSqrt -= 1
            stateRecord[n] = min + 1
            return min + 1
```
