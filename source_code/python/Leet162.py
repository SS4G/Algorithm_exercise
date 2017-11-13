class Solution(object):
    def findPeakElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return 0

        for i in range(len(nums)):
            if i == 0:
                if nums[i] > nums[i + 1]:
                    return i
            elif i == len(nums) - 1:
                if nums[i] > nums[i - 1]:
                    return i
            else:
                if nums[i - 1] < nums[i] > nums[i + 1]:
                    return i

if __name__ == "__main__":
    s = Solution()
    arr = [3, 2]
    idx = s.findPeakElement(arr)
    print(idx)

