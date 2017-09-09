import math
class Solution(object):
    def isUgly(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num==1 :
            return True
        elif num<1:
            return False
            
        end_num=int(math.sqrt(num)//1)
        prime_list=[]
        i=2
        while num>1:
            if num%i ==0 :
                num=int(num//i)
                prime_list.append(i)
            else :
                i+=1
            
        sum_2_3_5=prime_list.count(2)+prime_list.count(3)+prime_list.count(5)
        if sum_2_3_5<len(prime_list):
            return False
        else:
            return True
                
s=Solution()
print(s.isUgly(6        ))
print(s.isUgly(8        ))
print(s.isUgly(1        ))
print(s.isUgly(14       ))
print(s.isUgly(937351770))