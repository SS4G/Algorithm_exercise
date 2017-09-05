# leetcode 278
## Question
#### First Bad Version
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
## Answer
我的方法主要是使用二分法 就类似于函数求根的二分法
mid=(up+down)//2
但是这其中需要对几种特殊的情况作讨论
只有一个版本的情况 即第一次就有up=1
每次需要对up 和down区间的长度进行判断 如果有up-down=1
那么就不应该继续求中值 否则由于证书出发的问题将进入死循环


##### code

```
class Solution(object):
    def firstBadVersion(self, n):
        """
        :type n: int
        :rtype: int
        """
        up=n
        down=1
        if isBadVersion(1):
            return 1
        if up==1:
            return 1
        mid=(up+down)//2
        
        statue_pre=isBadVersion(1)    
        statue_mid=isBadVersion(2)    
        if not statue_pre==statue_mid :
            return 2
       
        statue_pre=isBadVersion(mid-1)
        statue_mid=isBadVersion(mid)     
        while statue_pre==statue_mid:
            if statue_mid==False:#stills good version
                down=mid
            else :
                up=mid
            
            mid=(up+down)//2
            #if mid==1: #noneed 
            if up-down==1:
                return up
            else :
                statue_pre=isBadVersion(mid-1)
                statue_mid=isBadVersion(mid)       
        return mid
```
