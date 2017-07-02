## 229. Majority Element II

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

#### tips
使用Boyer-Moore Majority Vote algorithm
具体按参见 博文 

[Boyer-Moore 算法](http://blog.csdn.net/u012501459/article/details/47128171)
这个算法对于 Leet 169 同样适用

需要注意的是代码中的循环各个部分的顺序 应该防止 a0 a1 为同一个数

以输出的观点看 最终剩下的只有两个数 说明其他的都被扔掉了 但是在扔掉的过程中， 如果想把两个大于1/3的扔掉的最多 按照这个规则的话 只能是 一个小于1/3的 与两个不同的 大于1/3的 但是就算这样最终剩下的也只能是 两个大于1/3的 这还是不算 在一次扔掉中有大于等于两个小于1/3的数字 那样剩下的更是大于1/3的 直接想里面发生了什么比较困难， 但是只看出口 很容易理解这个算法
#### mycode

```
class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        c0 = 0
        a0 = None
        c1 = 0
        a1 = None
        for n in nums:
            if a0 is not None and n == a0:
                c0 += 1
                continue

            if a1 is not None and n == a1:
                c1 += 1
                continue

            if a0 is None:
                a0 = n
                c0 = 1
                continue

            if a1 is None:
                a1 = n
                c1 = 1
                continue

            c0 -= 1
            c1 -= 1
            if c0 == 0:
                a0 = None
            if c1 == 0:
                a1 = None
        res = []
        if a0 is not None and nums.count(a0) > len(nums)//3:
            res.append(a0)
        if a1 is not None and nums.count(a1) > len(nums)//3:
            res.append(a1)
        return res
```
