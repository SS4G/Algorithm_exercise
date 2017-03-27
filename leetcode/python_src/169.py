class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        p=len(nums)
        checked_list=[]
        for k in nums:
            if k not in checked_list:
                checked_list.append(k)#if checked add this val in checked list
                if nums.count(k)>(p//2):
                    return k
               
            
        return None       
s=Solution() 
b=[6,5,6]
a=s.majorityElement(b)            
print(a)
