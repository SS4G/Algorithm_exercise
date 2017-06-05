import operator
class Solution(object):
    def containsNearbyAlmostDuplicate(self, nums, k, t):
        """
        :type nums: List[int]
        :type k: int
        :type t: int
        :rtype: bool
        """
        # k is the indices
        # t is value indices
        bucketNum = len(nums)//k if len(nums) % k == 0 else len(nums)//k+1
        oneBucket = [0, -0xffffffff, 0xffffffff]  # [elementNum, max, min]
        buckets = [oneBucket[:], ] * bucketNum

