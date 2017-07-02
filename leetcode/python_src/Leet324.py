class Solution(object):
    def wiggleSort(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        size = len(nums)
        snums = sorted(nums)
        for x in list(range(1, size, 2)) + list(range(0, size, 2)):
            nums[x] = snums.pop()
        return nums



if __name__ == "__main__":
    s = Solution()
    #a0 = [1, 5, 1, 1, 6, 2, 4]
    a0 = [5, 4, 5, 6]
    #a0 = [1,3,2,2,3,1]
    s.wiggleSort(a0)
    print(a0)