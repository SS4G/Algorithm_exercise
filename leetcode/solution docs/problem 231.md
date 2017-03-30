# leetcode 231
## Question
#### Power of Two

Given an integer, write a function to determine if it is a power of two.
## Answer
需要注意题目没说非负
所以测试样例中有负数
而bin（-16）=-0b10000
所以代码中要注意排查负数
代码的思想是二进制中只有一个字符1 就是2的幂（排除负数后）

##### code

```Python
class Solution(object):
    def isPowerOfTwo(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n <=0:
            return False
        
        bin_str=bin(n)
        list_bin=list(bin_str)
        
        if list_bin.count('1') ==1:
            return True
        else :
            return False
```
