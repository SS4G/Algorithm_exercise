## 201. Bitwise AND of Numbers Range 

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
For example, given the range [5, 7], you should return 4. 

#### tips
就是判断某个区间内的数字是否存在某一位可以是0
首先 k = m & n k中为0的位必然最后面结果是0 k中不是0的位要判断一下， 看如果把n的这位变为0 相当于给n减去一个2的幂后 这个数字是否还>=m 如果不是 那么这位到最后肯定是1

#### mycode

```
class Solution(object):
    def rangeBitwiseAnd(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        if m > n:
            m, n = n, m
        m = m & 0xffffffff
        n = n & 0xffffffff
        k = m & n
        record = [True, ]*32
        for i in range(32):
            mask = 0x00000001 << i
            if mask & k == 0:
                record[i] = False
                # print(i)
                continue
            else:
                res0 = n & (~mask)  # try to clear
                if res0 >= m:
                    record[i] = False
                    continue
        res = 0
        for i in range(32):
            mask = 0x00000001 << i
            if record[i]:
                res |= mask
        return res
```
