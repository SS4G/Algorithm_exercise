#leetcode test runs 81 ms
class Solution(object):
    def plusOne(self, digits):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        res=[]
        carry=1
        p=0
        digits.reverse()
        for single_digits in digits:
            if  carry+single_digits >=10:
                p=carry+single_digits
                res.insert(0,p-10)
                carry=1 #keep carry
                #print(res)
            else :
                p=carry+single_digits
                res.insert(0,p)
                carry=0 #keep carry
                #print(res)
        if carry==1 :
            res.insert(0,1) 
        
        return res
        
s=Solution()
digits=[1,2,3]
print(s.plusOne(digits))
digits=[9,9,9]
print(s.plusOne(digits))