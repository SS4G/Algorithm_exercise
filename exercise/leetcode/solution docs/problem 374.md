# leetcode 374
## Question
#### Guess Number Higher or Lower
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):


```
-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
```

Example:

```
n = 10, I pick 6.

Return 6.
```

## Answer
主要是使用二分法来加快查找进程
二分法在整数应用中要注意
在up-down<=2的情况下就可以直接确定结果
不可以继续再进行二分处理 否则会陷入死循环
如 
up=3
down=1
如果进行二分处理
如果正确的结果在2 上 那么
新的 down=1 相当于 新的up down与之前的up down完全处在一个状态下 所以如此往复就进入了死循环
所以 up-down <=2作为最后一次循环

##### code 
guess函数的实现 注意不要把题目的意思搞反了
```
# The guess API is already defined for you.
# @param num, your guess
# @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
def guess(num):
    std=2
    if num>std:
        return -1
    elif num==std:
        return 0
    else :
        return 1
```

```
class Solution(object):
    def guessNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        #1,2 ->    if guess 2  =dead circle
        #1,2,3 ->  1 if guess is 2 =dead circle
        #1,2,3,4 ->2
        #result define
        HIGHER=1
        LOWER=-1
        EQUAL=0
        
        up=n
        down=1
        if up == down:
            return up
        elif up-down==1:
            return up if guess(up)==EQUAL else down        
        elif up-down==2:
            if guess(up)==EQUAL:
                return up
            elif guess(up-1)==EQUAL:
                return up-1
            elif guess(down)==EQUAL:
                return down
        
        my_num=(up+down)//2
        res=guess(my_num)
        while res!=EQUAL:
            if res==LOWER:
                up=my_num  
            else:
                down=my_num
            print("up=",up,"down=",down)
            if up-down<=2:
                if up == down:
                    return up
                elif up-down==1:
                    return up if guess(up)==EQUAL else down        
                elif up-down==2:
                    if guess(up)==EQUAL:
                        return up
                    elif guess(up-1)==EQUAL:
                        return up-1
                    elif guess(down)==EQUAL:
                        return down 
            
            my_num=(up+down)//2
            res=guess(my_num)            
        return my_num
```
