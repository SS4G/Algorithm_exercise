class Solution(object):
    def wiggleSort(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        nums.sort()
        
        k=0
        l=-1
        nums_copy=[None]*len(nums)
        for i in range(len(nums_copy)):
            if i%2 ==0:
                nums_copy[i]=nums[k]
                k+=1
            else:
                nums_copy[i]=nums[l]
                l-=1      
        nums[:]=nums_copy[:]
        return 
        
    def find_first_same(self,dst_list):
        return
       
        
S2=Solution() 
test_case=[1, 5, 1, 1, 6, 4]
S2.wiggleSort(test_case)  
print("result")
print(test_case)
print("------")
test_case=[1, 3, 2, 2, 3, 1]
S2.wiggleSort(test_case) 
print("result")
print(test_case)
print("------") 


    
        
        
        
        
        
        
        
        
        
        
        
        