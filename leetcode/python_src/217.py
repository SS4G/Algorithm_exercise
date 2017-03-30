class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        for n in nums :
            if nums.count(n)>=2:#O(n^2) toooo SB!
                return True
            
        return False
        