# leetcode 326
## Question
#### Power of Three
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?

## Answer
本题最基本的思路是吧所给的整数一次一次的迭代对三取摸求余 看最后求余的结果是不是0 但这次换个不用循环的方法

使用对数运算

假设n为3的x次方
即
n=3^x
n x 均为正整数
所以
两边取对数
lg(3^x)=lg(n)
等价于
x*lg(3)=lg(n)
所以
x=lg(n)/lg(3)
所以 如果n是3的幂 结果x就应该是正整数
但由于计算机计算lg时是有误差的 直接通过这个计算出来肯能会有很小的误差
这就需要判断x是否近似为整数
如 x=3.0000000001 或 x=4.9999999999 这样的误差就可以认为是整数
只要测试用例所计算出来的结果 误差在±0.0000000001内即可认为理论结果为整数 返回True
如果出错，说明精度不够（本人第一次提交就遇到了这种问题 提高误差的精度后就没有这个问题了）


##### code
话说如果代码里面的打印语句忘了删除会严重影响代码效率o(︶︿︶)o
```Python
import math
class Solution(object):
    def isPowerOfThree(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n<=0 :
            return False
        else :
            x_round=math.log(n)/math.log(3) 
            float_error=x_round-(x_round//1)#整数误差范围
            if float_error<0.0000000000001 or float_error>0.9999999999999：#上下误差都要考虑
                return True
            else :
                return False
        return False
```
