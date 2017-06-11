class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        output = []
        mark = [False,]*len(nums)

    def gen(self, arr, st, output):
        for i range(, len(arr))