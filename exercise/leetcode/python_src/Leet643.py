class Solution:
    def findMaxAverage(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: float
        """
        curWindowSum = 0
        maxWindowSum = -0xffffffff
        for i in range(k - 1, len(nums)):
            if i == k - 1:
                curWindowSum = sum(nums[:k])
            else:
                curWindowSum += (nums[i] - nums[i - k])
            maxWindowSum = max(maxWindowSum, curWindowSum)

        return maxWindowSum / k

if __name__ == "__main__":
    s = Solution()
    arr = [1,12,-5,-6,50,3]
    print(s.findMaxAverage(arr, 4))