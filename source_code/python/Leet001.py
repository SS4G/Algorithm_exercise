class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        nums_copy=nums
        a=0
        b=0
        same_flag=0
       #print("nums_copy")

        #for k in nums_copy:
        #    if k >=target :
        #        del nums_copy[nums_copy.index(k):]
        #        break
        
        k_index=0
        l_index=1
        for k in nums_copy:#a_index
            l_s_index=k_index+1
            l_index=l_s_index
            for l in nums_copy[l_s_index:]:#b_index 
                if l+k == target:
                    #print("itering1")
                    #print("l="+str(l))#debug
                    #print("k="+str(k))#debug                    
                    a=k_index
                    b=l_index
                    break
                l_index+=1
            k_index+=1
                    

        index0=a
        index1=b
      
                    
        res=[]    
        #print(nums_copy)#debug
        if index0>index1 :
            res=[index1,index0]
        else :
            res=[index0,index1]
            
        return res
        
s=Solution()
#nums=[2, 7, 11, 15]
#target=9
#print(s.twoSum(nums, target))
#
#
#nums=[2, 7, 11,2,15]
#target=4
#print(s.twoSum(nums, target))
#
#nums=[-3,4,3,90]
#target=0
#print(s.twoSum(nums, target))


nums=[0,4,3,0]
target=0
print(s.twoSum(nums, target))