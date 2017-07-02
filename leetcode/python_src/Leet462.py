class Solution(object):
    def minMoves2(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums == []:
            return 0
        nums.sort()
        l = len(nums)
        mid = l >> 1
        midVal = nums[mid]
        """
        if len(nums) & 0x01:
            midVal = nums[mid]
        else:
            midVal = nums[mid]
        """
        return sum([abs(midVal - i) for i in nums])

if __name__ == "__main__":
    s = Solution()
    nums = [1,2,3]
    print(s.minMoves2(nums))



