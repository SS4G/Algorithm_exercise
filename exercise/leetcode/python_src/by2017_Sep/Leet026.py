class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        
        
        i=1
        whole_len=len(nums)
        if whole_len<=1:
            return whole_len
        while i<whole_len:
            if nums[i]==nums[i-1]:
                del(nums[i])
                whole_len-=1
            else:
                i+=1
        
        return whole_len
        
