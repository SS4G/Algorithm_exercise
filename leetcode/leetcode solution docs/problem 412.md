# leetcdoe 404
## Question 
#### Fizz Buzz
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,


```
Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
```
## Answer
主要是想说虽然直观上是可以用求余的方法来实现 但是求余是调用除法 然后调用减法才能得出结果 效率必然机器低下 不如考虑类似于 3进制
5进制 使用计数器来判断 直接看代码就明白了 能不用乘除法就不要用

##### code (beat 92% Python)
```
class Solution(object):
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        cnt3=0
        cnt5=0
        res=[]
        for i in range(1,n+1):
            cnt3+=1
            cnt5+=1
            if cnt3==3 and cnt5==5:
                cnt3=0
                cnt5=0
                res.append("FizzBuzz")
            elif cnt3==3:
                cnt3=0
                res.append("Fizz")
            elif cnt5==5:
                cnt5=0
                res.append("Buzz")
            else:
                res.append(str(i))
        
        return res
```
