class Solution(object):
    def isHappy(self, n):
        """
        :type n: int
        :rtype: bool
        """
        appear_list=[]
        tmp_square_add=0
        false_flag=0
        tmp_square_add=n
        pows_result=[0,1,4,9,16,25,36,49,64,81]
        while tmp_square_add!=1:
            
            #print(tmp_square_add)
            #input("kk")
            tmp_digits_list=self.todigits(tmp_square_add)
            tmp_square_add=0
            #print(appear_list)
            for p in tmp_digits_list:
                tmp_square_add+=pows_result[p] #use list check to reduce the run time 
            if tmp_square_add not in appear_list:
                appear_list.append(tmp_square_add)#add appeared 
            else :
                false_flag=1
                break
                            
        
        if false_flag :
            return False
        else :
            return True
        
        
    def todigits(self,num):
        """
        type num non-negtive-integer                
        """
        
        string_num=str(num)
        digits_num=len(string_num)
        digits_list=[None]*digits_num
        digit_dict={'0':0,'1':1,'2':2,'3':3,'4':4,'5':5,'6':6,'7':7,'8':8,'9':9}
        i=0
        for k in string_num:
            digits_list[i]=digit_dict[k]
            i+=1
        return digits_list
        
        
s=Solution()
print(s.isHappy(2))
li=[1,2,3,4,5,6]



