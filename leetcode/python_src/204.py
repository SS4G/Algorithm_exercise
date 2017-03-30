import math
class Solution(object):
    def countPrimes(self, n):
        """
        :type n: int
        :rtype: int
        """
        sum=0
        for i in range(n):
            if(self.is_prime(i)):
                sum+=1
        return sum        
        
    def is_prime(self,x):
        if x==0 or x==1:
            return False
        elif x==2:
            return True
        elif x&0x01 ==0:
            return False

        bond=int(math.sqrt(x))
        n=3
        while n<=bond:
            if x%n==0:
                return False
            n+=2
        return True
S=Solution()
print(S.is_prime(9))
print(S.is_prime(16))
print(S.is_prime(17))
print(S.countPrimes(0))
print(S.countPrimes(1))
print(S.countPrimes(2))
print(S.countPrimes(10))
print(S.countPrimes(499979))
print(3**3)


