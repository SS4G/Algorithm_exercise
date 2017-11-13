import math
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
     

S=Solution()
print(S.choose(4,2))
print(S.climbStairs(5))
print(S.climbStairs(1))
print(S.climbStairs(0))