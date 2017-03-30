class Solution(object):
    def isPowerOfTwo(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n <=0:
            return False
        
        bin_str=bin(n)
        list_bin=list(bin_str)
        
        if list_bin.count('1') ==1:
            return True
        else :
            return False

       
s=Solution()      
print(bin(-16))
print(s.isPowerOfTwo(13))
print(s.isPowerOfTwo(1))
print(s.isPowerOfTwo(2))