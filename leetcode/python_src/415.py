class Solution(object):
    def addStrings(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        l1=len(num1)
        l2=len(num2)
        if l1<=l2:
           num1,num2=num2,num1
           l1,l2=l2,l1
           #swap l1 l2 
        #l1>=l2 forever

        l2-=1
        l1-=1
        sum_list=[]
        carry=0
        while l2>=0:
            sum_bit=ord(num2[l2])+ord(num1[l1])-ord('0')-ord('0')
            #print(sum_bit,num2[l2],num1[l1])
            sum_bit+=carry
            if sum_bit>=10:
                carry=1
                sum_list.append(sum_bit-10)
            else:
                carry=0
                sum_list.append(sum_bit)
            l2-=1 
            l1-=1 
        #print(sum_list)
        
        while l1>=0:
            sum_bit=ord(num1[l1])-ord('0')
            sum_bit+=carry
            if sum_bit>=10:
                carry=1
                sum_list.append(sum_bit-10)
            else:
                carry=0
                sum_list.append(sum_bit)
            l1-=1 
            
        if carry==1:
            sum_list.append(1)
        res_list=[]
        sum_list.reverse()
        #print(sum_list)
        for i in sum_list:
            res_list.append(str(i))
        return "".join(res_list)       
            
s=Solution()            
z=s.addStrings("123","5456")         
print(z)          
        
        