class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        cnt = [0, 0, 0]
        for num in nums:
            cnt[num] += 1
        absIndex = 0
        for i in range(3):
            while cnt[i] > 0:
                nums[absIndex] = i
                absIndex += 1
