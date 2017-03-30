class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        nums.sort()
        n_last=None
        for n in nums :
            if n_last==n:
                return True
            n_last=n
            
        return False
        