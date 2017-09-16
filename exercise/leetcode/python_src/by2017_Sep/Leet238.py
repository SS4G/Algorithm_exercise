class Solution(object):
    def productExceptSelf(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        left_res = []
        right_res = []

        n =len(nums)
        if n == 2:
            return [nums[1], nums[0]]

        part_product = 1

        i = 0
        while i < n:
            left_res.append(part_product)
            part_product *= nums[i]
            i += 1

        i = n-1
        part_product = 1
        while i >= 0:
            right_res.append(part_product)
            part_product *= nums[i]
            i -= 1
        right_res.reverse()
        return [left_res[i]*right_res[i] for i in range(n)]