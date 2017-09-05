##  179. Largest Number

Given a list of non negative integers, arrange them such that they form the largest number.

For example, given 
```
[3, 30, 34, 5, 9],
```
 the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.


#### tips
主要是看如何定义一种新的字符串排序  既然数字的总长度是固定的 所以可以先按照字典序 对首字母进行排序。 但是按照字典序进行排序会有一部分解决不了的问题。 如: 33312 和 333
或者 33352 和 333
这种问题就是 a是b的前缀， 所以 a 和b的大小不能简单的按照字典序 
假设 s0 = A s1 = AB A 是 s0 s1 的共同前缀 所以 要比较 AAB 和 ABA 那种组成的数字更大 
也就是 组合后的后半部分 AB 和 BA 哪个大  结论是
```
if AB > BA -> s0 > s1
else s0 < s1 
```
这和 AB BA 的大小关系刚好是反的 重新定义比较关系了以后 用一个通俗的排序算法就可以了 本题使用的是冒泡排序 所以很慢

#### mycode
```Python
class Solution:
    # @param {integer[]} nums
    # @return {string}
    def largestNumber(self, nums):
        nums = [str(j) for j in nums]
        for i in range(len(nums)):
            for j in range(i, len(nums)):
                if self.cmpNum(nums[i], nums[j]) < 0:
                    nums[i], nums[j] = nums[j], nums[i]
        res = "".join(nums)
        if res[0] == '0':
            res = "0"
        return res

    def cmpNum(self, s0, s1):
        minLen = min(len(s0), len(s1))
        for i in range(minLen):
            if s0[i] < s1[i]:
                return -1
            elif s0[i] > s1[i]:
                return 1
            else:
                pass

        if len(s0) == len(s1):
            return 0
        else:
            if len(s0) > len(s1):
                return -self.cmpNum(s0, s0[minLen:]+s1)
            elif len(s1) > len(s0):
                return self.cmpNum(s1, s1[minLen:] + s0)
            return 0
```
