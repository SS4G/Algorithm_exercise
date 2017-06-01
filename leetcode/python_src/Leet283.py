class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        copy0=[]
        copy1=[]
        copy0[:]=nums[:]
        length=len(nums)
        for k in range(length):
            if copy0[k] :
                copy1.append(copy0[k])
        z=len(copy0)-len(copy1)
        for k in range (z):
            copy1.append(0)
        print(copy1)
        nums[:]=copy1[:]
        return 
            
x=Solution()
a=[1,0,9,0,7,8,0,0]
x.moveZeroes(a) 
print(a)  
        
