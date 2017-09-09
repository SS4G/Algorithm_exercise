import math
class Solution(object):
    def isPowerOfThree(self, n):
        """
        :type n: int
        :rtype: bool
        """
        if n<=0 :
            return False
        else :
            x_round=math.log(n)/math.log(3)  
            print("equal n="+str(n))
            print(x_round)  
            float_error=x_round-(x_round//1)#整数误差范围
            if float_error<0.0000000000001 or float_error>0.9999999999999:
                return True
            else :
                return False
        return False
s=Solution()
print(s.isPowerOfThree(9))
print(s.isPowerOfThree(27))
print(s.isPowerOfThree(4))
print(s.isPowerOfThree(3))
print(s.isPowerOfThree(1))
print(s.isPowerOfThree(0))
print(s.isPowerOfThree(243))
print(s.isPowerOfThree(-3))