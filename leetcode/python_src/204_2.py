class Solution(object):
    def countPrimes(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n==0 or n==1: #对于 n=0 n=1 的情况直接返回0
            return 0
        prime=[True]*n  #对于n>=2 的情况 首先构建一个内容为全真的数组    
                        #如果 5 为素数 那么 prime[5]=True 10 为合数 那么prime[10]=False
        if(n>=2):
            prime[0]=False#0,1不为素数股结果为0
            prime[1]=False#0,1不为素数股结果为0
            for i in range(2,int(n**0.5)+1):#选择2~int(sqrt(n))+1 的数字进行判断是应为下面i*i<n
                if prime[i]:# 如果 i是素数  
                    prime[i*i:n:i]=[False]*len(prime[i*i:n:i]) #那么 [i*i~n)中i的倍数均不是素数  
                                    # i*i 之前的i的倍数 由于i>=2 必然有另一个比i小的因子 这个因子
                                    #在别的素数进行倍数判断时已经被检索过了 已经知道是不是素数了所以不必检索
        return sum(prime)  
        
S=Solution()
print(S.countPrimes(0))
print(S.countPrimes(1))
print(S.countPrimes(2))
print(S.countPrimes(10))
print(S.countPrimes(499979))
print(3**3)
        
        
        