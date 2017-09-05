# leetcode 9
## Question
Determine whether an integer is a palindrome. Do this without extra space.
## Answer
转换成字符串 然后从两端逐个开始比较
如果要求不使用额外的空间 就先判断数字位数然后用求模和求余来逐位比较 思想是一样的

##### code

```Python
class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """        
        #valid test case
        #123321 true
        #-1     false
        #1      true
        #0      true
        #-0     false
        p=str(x)
        l=len(p)
        k=0
        neg_k=l-1
        for k in range(int(l//2)):
            if p[k]!=p[neg_k] :
                return False
            else:
                k+=1
                neg_k-=1
        return True
```

```
class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """        
        #valid test case
        #123321 true
        #-1     false
        #1      true
        #0      true
        #-0     false
        if x<0:
            return False         
        h=1
        p=int(x//h)
        x=int(x)
        while p>=10:
            h*=10
            p=int(x//h)            
            
        l=int(x//h)
        r=x%10
        while x>0:
            if l!=r:
                return False
            else:   
                x=x%h
                h=int(h//100)
                x=int(x//10)
                if h==0 :
                    break
                l=int(x//h)
                r=x%10        
        return True
```
