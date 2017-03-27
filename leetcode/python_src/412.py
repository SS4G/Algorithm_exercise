class Solution(object):
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        cnt3=0
        cnt5=0
        res=[]
        for i in range(1,n+1):
            cnt3+=1
            cnt5+=1
            if cnt3==3 and cnt5==5:
                cnt3=0
                cnt5=0
                res.append("FizzBuzz")
            elif cnt3==3:
                cnt3=0
                res.append("Fizz")
            elif cnt5==5:
                cnt5=0
                res.append("Buzz")
            else:
                res.append(str(i))
        
        return res
          
s=Solution()
print(s.fizzBuzz(15))           
            
