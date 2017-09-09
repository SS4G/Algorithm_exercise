# leetcode 7
## Question
#### Reverse Integer
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
## Answer
1. 一般的方法是原始数转化成字符串
2. 对字符串进行翻转
3. 将翻转后的字符处再转化成数字

但本题的要点在于是否翻转后溢出

系统界定翻转后是否溢出的标准是32位存储
正数 <2^31-1
负数 >-2^31

但由于python 支持64 位的整数存储所以即使超出上述便捷也不会有问题   所以要专门设定 32位正数的边界值来满足题目的要求

##### code

```Python
class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x<0:
            x=-x
            x_str=str(x)
            x_str=list(x_str)
            x_str.reverse()
            negtive_res=-self.to_int(x_str)
            return  negtive_res if negtive_res>=(-2147483648) else 0 #overflow handeller MIN_POSITIVE_INT<(0x01<<31)
        else :
            x_str=str(x)
            x_str=list(x_str)
            x_str.reverse()
            positive_res=self.to_int(x_str)            
            return positive_res if positive_res<(2147483648) else 0 #overflow handeller MAX_POSITIVE_INT<(0x01<<31)
            
    def to_int(self,list_x):
        x=''
        for p in list_x:
            x+=p
        return int(x)
```


