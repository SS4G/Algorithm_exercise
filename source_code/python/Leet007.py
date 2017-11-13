class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x<0:
            x=-x
            x_str=str(x)
            x_str=list(x_str)
            x_str.reverse()
            negtive_res=-self.to_int(x_str)
            return  negtive_res if negtive_res>=(-2147483648) else 0 #overflow handeller MIN_POSITIVE_INT<(0x01<<31)
        else :
            x_str=str(x)
            x_str=list(x_str)
            x_str.reverse()
            positive_res=self.to_int(x_str)            
            return positive_res if positive_res<(2147483648) else 0 #overflow handeller MAX_POSITIVE_INT<(0x01<<31)
            
    def to_int(self,list_x):
        x=''
        for p in list_x:
            x+=p
        return int(x)
        
        
        
    
s=Solution()
print(s.reverse(12345))
print(s.reverse(-12345))
print(s.reverse(0))
print(s.reverse(-0))
print(s.reverse(-123))
print(9646324351>(0x01<<32))



