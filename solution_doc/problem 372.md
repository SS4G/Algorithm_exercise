## 372. Super Pow 
Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.


```
Example1:

a = 2
b = [3]
```


Result: 8

```
Example2:

a = 2
b = [1,0]
```

Result: 1024

#### ps:
输入的b的长度可能有长达1000多

#### tips
首先有一个知识点
- ab%m = (a%m)*(b%m)%m

根据这个知识点 举个例子
设f(a, 1234567) 表示 a^1234567 % m    
则 
```
a^1234567%m = ((a^123456%m)^10 % m)*(a^7%m)%m

then :
f(a, 1234567) = f(a, 123456)^10 % m)*(a^7%m)%m
```
所以最后这个题目可以用递归实现  
需要注意的一点是因为递归的层次数和b的长度成正比， 所以 不应该再递归中对b使用分片操作
这会导致b的多个副本 出现最终导致复杂度接近len(b)^2 MLE 所以应该对b使用原地操作  
- superPowUnSliced 表示使用原地操作 不使用切片操作

#### mycode

```
class Solution(object):
    def superPow(self, a, b):
        """
        :type a: int
        :type b: List[int]
        :rtype: int
        """
        m = 1337
        return self.superPowUnSliced(a, b, len(b)-1)

    def superPowUnSliced(self, a, b, endIndex):
        m = 1337
        if endIndex == 0:
            return a**b[0] % m
        else:
            return ((self.superPowUnSliced(a, b, endIndex-1)**10) % m) * (a**b[endIndex] % m) % m
```
