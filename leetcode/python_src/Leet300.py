class Solution(object):
    def lengthOfLIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) <= 1:
            return len(nums)

        records = [0, ] * len(nums)
        for i in range(len(nums)):
            records[i] = 1
            for j in range(0, i + 1):
                if nums[i] > nums[j]:
                    records[i] = max(records[j] + 1, records[i])
        return max(records)


if __name__ == "__main__":
    s = Solution()
    arr = [2, 2, 2, 2]
    print(s.lengthOfLIS(arr))
