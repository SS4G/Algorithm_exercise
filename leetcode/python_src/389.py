class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        i=0
        len_l=len(nums)
        
        sum1=0
        sum2=0
        i=0
        while i <len_l:
            sum1+=nums[i]
            i+=2
        i=1
        while i <len_l:
            sum2+=nums[i]
            i+=2
        return sum1 if sum1>sum2 else sum2