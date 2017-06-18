## 397 Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:


```
Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
```

#### tips
如果是偶数 需要一次移位
如果是奇数 分为两种情况 倒数第二位是0 则 减一 此时加一 不会减少1 1的数量
否则加一 加一 进位会使得1 消失的更快  需要注意的是 如果此时n已经为 3 用减一操作 而不是加一 这个地方有点特殊
重复右移位 和 上述操作 直到n 变为 0x01
#### mycode
```Python
class Solution(object):
    def integerReplacement(self, n):
        """
        :type n: int
        :rtype: int
        """
        n &= 0xffffffff
        cnt = 0

        while n != 0x01:
            if n & 0x01 != 0:  # odd
                if n == 0x03:
                    n -= 1
                elif (n & 0x02) != 0:  # pre bit is 1
                    n += 1
                else:
                    n -= 1
            else:
                n >>= 1
            cnt += 1
        return cnt
```
