# leetcode 70
## Question
#### Climbing Stairs
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
## Answer
爬楼梯只有爬一节和爬两节的区别 列举出来可以看出其实是求排列组合C(k,n)    <n 选 k>的题目

如 有5节楼梯

则有
C(0,5)+C(1,4)+C(2,3)

##### code
```Python
class Solution(object):
    def choose(self,n,k):
        i=0
        up=1
        while i<k:
            up*=n
            n-=1
            i+=1
        down=math.factorial(k)
        return int(int(up)//int(down))
        
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        nums_of_2=n>>1
        i=0
        sums=0
        while i<=nums_of_2:
            sums+=self.choose(n-i,i)   
            i+=1
        return sums
```
