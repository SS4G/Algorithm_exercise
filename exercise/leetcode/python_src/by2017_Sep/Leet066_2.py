#leetcode test runs 56 ms
class Solution(object):
    def plusOne(self, digits):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        res=[0]*(len(digits)+1)
        carry=1
        p=0
        index=len(digits)
        digits.reverse()
        
        for single_digits in digits:
            if  carry+single_digits >=10:
                p=carry+single_digits
                #res.insert(0,p-10)
                res[index]=p-10
                carry=1 #keep carry
                #print(res)
                index-=1
            else :
                p=carry+single_digits
                #res.insert(0,p)
                res[index]=p
                carry=0 #keep carry
                index-=1
                #print(res)
        if carry==1 :
            res[index]=1 
        else :
            del(res[0])
        
        return res
        
        
        
s=Solution()
digits=[1,2,3]
print(s.plusOne(digits))
digits=[2,9,9]
print(s.plusOne(digits))
digits=[9,9,9]
print(s.plusOne(digits))