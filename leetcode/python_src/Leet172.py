class Solution(object):
    def trailingZeroes(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n<5:
            return 0
        tmp=n
        sum=0
        while tmp>0:
            tmp=tmp//5
            tmp=int(tmp)
            sum+=tmp
        return sum
        
    
 
s=Solution()
print(s.trailingZeroes(128))



