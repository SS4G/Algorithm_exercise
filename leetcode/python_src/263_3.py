class Solution(object):
    def isUgly(self, num):
        """
        :type num: int
        :rtype: bool
        """
        flag_2=0
        flag_3=0
        flag_5=0
        if num==1:
            return True
        elif num<1:
            return False 
        while num>1:
            if flag_2==0:
                if num %2 ==0:
                    num//=2
                    num=int(num)
                else:
                    flag_2=1
            elif flag_3==0:
                if num %3 ==0:
                    num//=3
                    num=int(num)
                else:
                    flag_3=1
            elif flag_5==0:
                if num %5 ==0:
                    num//=5
                    num=int(num)  
                else:
                    flag_5=1            
            else :
                return False
        return True
s=Solution()
print(s.isUgly(6        ))
print(s.isUgly(8        ))
print(s.isUgly(1        ))
print(s.isUgly(14       ))
print(s.isUgly(937351770))        
