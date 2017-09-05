class Solution(object):
    def subsets(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        bits = len(nums)
        nums.sort()
        result = []
        for i in range(2**bits):
            result.append(self.reflect(nums, i, bits))
        return result

    def reflect(self, nums, x, bits):
        mask = 0x01
        res = []
        for i in range(bits):
            if mask & x:
                res.append(nums[i])
            mask <<= 1
        return res

if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3]
    # print(s.genSubsetsOfLength(arr, 4, 0))
    print(s.subsets(arr))