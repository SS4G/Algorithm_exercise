## 89. Gray Code Add to List

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
#### Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
#### tips
根据格雷码的反射性质
其大周期为   
perido = 1 << (2+bit)
字符周期为 patternPerido = 1 << bit  
每个大周期里面连续的字符分布都符合[0,1,1,0]规律
如 最低位 [0,1,1,0]
右起第二位 [0,0,1,1,1,1,0,0] 相当于吧第一位的情况作了*2的扩展 然后就利用这个性质来根据 位置i来生成格雷码
#### mycode
```Python
class Solution(object):
    def grayCode(self, n):
        """
        :type n: int
        :rtype: List[int]
        """
        resultLength = 2**n
        oneResult = [0, ]*n
        allResults = [0, ]*resultLength
        pattern = [0, 1, 1, 0]
        for i in range(resultLength):
            for bit in range(n):
                # perido = 1 << (2+bit)
                # patternPerido = 1 << bit
                # oneResult[bit] = pattern[(i % perido) // patternPerido]
                oneResult[bit] = pattern[(i & ((1 << (2+bit))-1)) >> bit]
            res = 0
            for p in range(n):
                res += (1 << p) if oneResult[p] else 0
            allResults[i] = res
        return allResults
```
