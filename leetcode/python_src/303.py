class NumArray(object):
    def __init__(self, nums):
        """
        initialize your data structure here.
        :type nums: List[int]
        """
        self.last_i=0
        self.last_j=0
        self.last_sum=0
        self.nums2=nums
        self.firstcall=1
    def sumRange(self, i, j):
        """
        sum of elements nums[i..j], inclusive.
        :type i: int
        :type j: int
        :rtype: int
        """ 
        if not firstcall:        
            if   (self.last_i<=i<=self.last_j) and (self.last_i<=j<=self.last_j):                
                res=self.last_sum-sum(self.nums2[self.last_i:i])-sum(self.nums2[j+1:self.last_j+1]) 
            elif (self.last_i<=i<=self.last_j) :
                res=self.last_sum-sum(self.nums2[self.last_i:i])+sum(self.nums2[self.last_j+1:j+1])
            elif (self.last_i<=j<=self.last_j) :
                res=self.last_sum-sum(self.nums2[j+1:self.last_j+1])+sum(self.nums2[i:self.last_i])
            else:
                res=sum(self.nums2[i:j+1])
        else:
            res=sum(self.nums2[i:j+1]) 
            firstcall=0
             
             
        self.last_i=i
        self.last_j=j
        self.last_sum=res
        return res
nums=[1,2,3,4,5,6,7,8,9,10,-9,-8,7,8,9,3,4]
#---------|         |-----
#-------&                      &------  


       
s=NumArray(nums)
i=1
j=11      
print(s.sumRange(i,j)==sum(nums[i:j+1]))   
i=2
j=7      
print(s.sumRange(i,j)==sum(nums[i:j+1]))         
i=5
j=10      
print(s.sumRange(i,j)==sum(nums[i:j+1]))
i=1
j=7      
print(s.sumRange(i,j)==sum(nums[i:j+1]))
i=8
j=12      
print(s.sumRange(i,j)==sum(nums[i:j+1]))

# Your NumArray object will be instantiated and called as such:
# numArray = NumArray(nums)
# numArray.sumRange(0, 1)
# numArray.sumRange(1, 2)