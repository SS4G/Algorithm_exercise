class Solution(object):
    def twoSum(self, numbers, target):
        """
        :type numbers: List[int]
        :type target: int
        :rtype: List[int]
        """
        k_index=0
        numbers_length=len(numbers)
        zip_length=0
        val_list=[]
        index_list=[]
        first_flag=0
        last_k=0
        # compress process
        for k in numbers:
            if k!=last_k or first_flag==0:
                val_list.append(k)
                index_list.append(k_index) 
            last_k=k
            first_flag=1
            k_index+=1
        zip_length=len(index_list)
        #print("val_list  ",val_list)
        #print("index_list",index_list)
        k_index=0
        j_index=0
        #print("zip_len",zip_length)
        for k in val_list:
            if (target>>1)==k:# special situation : target=k*2
                if index_list[k_index]==numbers_length-1:#k is the final value 
                                      #and this value appear only one time in numbers
                    return [None,None]#this situation won't appear if input array is correct
                elif k_index==zip_length-1:#k is the final value 
                    return [index_list[k_index]+1,index_list[k_index]+2]#but this value appear more than one time in numbers
                elif index_list[k_index]+1<index_list[k_index+1]+1:#k is not final value but k just appera only once in numbers
                    return [index_list[k_index]+1,index_list[k_index]+2]
                else :
                    continue
            else:
                j_index=k_index+1
                for j in val_list[k_index+1:]:
                    if k+j == target:
                        #print("k=",k,"j=",j)
                        #print("k_index=",k_index,"j_index",j_index)
                        #print("index_list[k_index]=",index_list[k_index],"index_list[j_index]=",index_list[j_index])
                        return [index_list[k_index]+1,index_list[j_index]+1]
                    j_index+=1
                
            k_index+=1                   
                
        return [None,None]   

        
a=Solution()
sorted_list=[5,25,75]
target=100
print(a.twoSum(sorted_list,target))
sorted_list=[3,24,50,79,88,150,345]
target=200
print(a.twoSum(sorted_list,target))
#sorted_list=[2,7,11,15]
#target=9
#print(a.twoSum(sorted_list,target))
#sorted_list=[2,2,2,7,7,7,11,15]
#target=9
#print(a.twoSum(sorted_list,target))
#sorted_list=[2,2,2,7,7,7,11,15]# target=2*k k in middle more than once
#target=4
#print(a.twoSum(sorted_list,target))
#sorted_list=[2,2,2,7,7,7,11,15,15]# target=2*k k in middle more than once
#target=30
#print(a.twoSum(sorted_list,target))
#sorted_list=[2,2,2,7,7,7,11,13,15]# target=2*k k in middle more than once
#target=30
#print(a.twoSum(sorted_list,target))
#sorted_list=[1,2,3,7,7,7,11,13,15]# target=2*k k in middle more than once
#target=4
#print(a.twoSum(sorted_list,target))