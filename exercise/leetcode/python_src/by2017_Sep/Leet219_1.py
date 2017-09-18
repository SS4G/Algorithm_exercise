class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        dicts={}
        length=len(nums)
        for i in range(length):
            if nums[i] not in dicts:
                dicts[nums[i]]=i
            else:
                if i-dicts[nums[i]]<=k:
                    return True
                else :
                    dicts[nums[i]]=i
        return False
        