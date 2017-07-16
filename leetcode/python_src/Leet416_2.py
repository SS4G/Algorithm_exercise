class Solution(object):
    def canPartition(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) <= 1:
            return False
        nums.sort()
        sum0 = sum(nums)
        if sum0 & 0x01 != 0:
            return False
        else:
            dpDict = [{} for i in range(len(nums))]
            return self.placeRecursive(nums, len(nums) - 1, sum0 >> 1, dpDict)

    def placeRecursive(self, nums, edIndex, targetSum, dpRec):
        if targetSum in dpRec[edIndex]:
            return dpRec[edIndex][targetSum]
        else:
            if targetSum == 0:
                res = True
            elif targetSum < 0:
                res = False
            elif edIndex == 0:
                res = True if targetSum == nums[edIndex] else False
            else:
                r0 = self.placeRecursive(nums, edIndex - 1, targetSum - nums[edIndex], dpRec)  # place
                r1 = self.placeRecursive(nums, edIndex - 1, targetSum, dpRec)  # not place
                res = r0 or r1
            dpRec[edIndex][targetSum] = res
            return res


if __name__ == "__main__":
    s = Solution()
    nums = [1, 5, 11, 5]
    nums = [1, 2, 3, 5]
    #nums = [1,2,3,4,5,6,7]
    r = s.canPartition(nums)
    print(r)

