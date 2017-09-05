from functools import reduce
class Solution(object):
    def makesquare(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        totalLength = sum(nums)
        if totalLength % 4 != 0:
            return False
        else:
            sideLen = totalLength >> 2
        nums.sort(reverse=True)
        return self.dfsRecursive(nums, [0, ] * 4, 0, sideLen)

    def dfsRecursive(self, nums, sums, index, target):
        if index == len(nums) - 1:
            if sums[0] == target and sums[1] == target and sums[2] == target and sums[3] == target:
                return True
        else:
            return False

        for i in range(4):
            if sums[i] + nums[index] > target:
                continue
            sums[i] += nums[index]
            if self.dfsRecursive(nums, sums, index + 1, target):
                return True
            sums[i] -= nums[index]
        return False

if __name__ == "__main__":
    s = Solution()
    nums = [5,5,5,5,16,4,4,4,4,4,3,3,3,3,4]
    print(s.makesquare(nums))
