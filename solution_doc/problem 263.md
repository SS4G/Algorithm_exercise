# leetcode 263
## Question
#### Ugly Number
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.
## Answer 
有两种解决方法
一种是对这个数分解质因数，然后看结果里面是否只有2,3,5
另一种方法是将这个数对 2，3,5,取余，如果取余结果为0 就整除2,3,5 直到结果为1 （说明质因数只有2,3,5） 或者对2,3,5 取余结果都不为0说明 含有其他质因子

第一种方法 容易超时
##### code 

```Python
import math
class Solution(object):
    def isUgly(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num==1 :
            return True
        elif num<1:
            return False
            
        end_num=int(math.sqrt(num)//1)
        prime_list=[]
        i=2
        while num>1://分解质因数
            if num%i ==0 :
                num=int(num//i)
                prime_list.append(i)
            else :
                i+=1
            
        sum_2_3_5=prime_list.count(2)+prime_list.count(3)+prime_list.count(5)
        if sum_2_3_5<len(prime_list):
            return False
        else:
            return True
```
第二种方法
##### 代码

```Python
class Solution(object):
    def isUgly(self, num):
        """
        :type num: int
        :rtype: bool
        """
        flag_2=0
        flag_3=0
        flag_5=0
        if num==1:
            return True
        elif num<1:
            return False 
        while num>1:
            if flag_2==0:
                if num %2 ==0:
                    num//=2
                    num=int(num)
                else:
                    flag_2=1
            elif flag_3==0:
                if num %3 ==0:
                    num//=3
                    num=int(num)
                else:
                    flag_3=1
            elif flag_5==0:
                if num %5 ==0:
                    num//=5
                    num=int(num)  
                else:
                    flag_5=1            
            else :
                return False
        return True
```

