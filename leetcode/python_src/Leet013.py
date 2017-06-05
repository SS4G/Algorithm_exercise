class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        length=len(s)
        nums=s
        k=0
        bit=0
        sum=0
        last_flag=0
        while k<length-1 :
            if(self.val_convert(nums[k])<self.val_convert(nums[k+1])):
                bit=self.val_convert(nums[k+1])-self.val_convert(nums[k])
                last_flag=1
                k+=2
            else :
                bit=self.val_convert(nums[k])
                last_flag=0
                k+=1
            
            sum+=bit
        if k<length:
            bit=self.val_convert(nums[k])
            sum+=bit
            k+=1
        return sum
        
        
        
    
    
    def val_convert(self,num):
        x={'I':1,'V':5,'X':10,'L':50,'C':100,'D':500,'M':1000}
        return x[num]
     
     
     
x=Solution()
print(x.romanToInt("I"))
print(x.romanToInt("V"))
print(x.romanToInt("IV"))
print(x.romanToInt("XX"))
print(x.romanToInt("XXXIX"))