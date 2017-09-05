## 264. Ugly Number II
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, and n does not exceed 1690.

#### tips
这个题目如果用暴力解法去解决的话 一个一个的ugly number去求的话肯定会超时 
所以我们根据丑叔的性质 使用类似于求质数表时候的晒标法那样 使用前面的结果去直接生成后面的结果

我们现在因为需要去求 第n个丑叔 所以需要保证我们生成的丑数是当前丑数集合中的下一个 而不是跳跃的

如 
1， 2 ， 3， 4， 6 就是失败的 跳过了5

假设当前的丑数质数因子为 abc 那么 先产生第一个丑数1 设置三个指针指向当前第一个丑数 然后求出 这个数 *a *b *c 取最小值 这个最小值就是下一个连续的丑数 然后需要检查到低是abc那个因子产生了这个丑数 然后就把那个因子对应的指针向前移动 有时候 产生下一个连续丑数的因子不只是一个 比如 2 * 3 = 6 3 * 2 = 6所以 应该使用多个连续的if判断 而不是 if else

这个的理论依据是 如果有当前的一个丑数集合 S 那么 下一个不属于集合的丑数必然 可以由这个集合中的某个丑数乘上一个丑数因子来产生 所以 可以用对应的指针来指向可能产生下一个丑数的 本个集合中的丑数 他们分别乘上丑数因子产生的丑数中必然有那个连续者 可以用反证法证明 如果不是必然推出矛盾

#### mycode

```
class Solution(object):
    def nthUglyNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        ugly = [1]
        i2, i3, i5 = 0, 0, 0
        while n > 1:
            u2, u3, u5 = 2 * ugly[i2], 3 * ugly[i3], 5 * ugly[i5]
            umin = min((u2, u3, u5))
            if umin == u2:
                i2 += 1
            if umin == u3:
                i3 += 1
            if umin == u5:
                i5 += 1
            ugly.append(umin)
            n -= 1
        return ugly[-1]
```
