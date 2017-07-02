## 386. Lexicographical Numbers

Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
#### tips
这个题目因为结果巨大所以应该使用 迭代器的思想 来根据上一数字 生成他的下一个字典序数字

主要见代码

#### mycode

```
class Solution(object):
    def lexicalOrder(self, n):
        """
        :type n: int
        :rtype: List[int]
        """

        res = [1, ] * n
        for i in range(1, n):
            last = res[i - 1]
            if last * 10 <= n:
                res[i] = last * 10
            else:
                while True:
                    lastBit = last % 10
                    if lastBit < 9:
                        last += 1
                        res[i] = last
                    else:
                        while last % 10 == 9:
                            last //= 10
                        last += 1
                    if last <= n:
                        break
                    else:
                        last //= 10
                res[i] = last
        return res
```
