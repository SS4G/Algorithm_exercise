class Solution(object):
    """
    status:TLE
    """
    def canPartition(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) <= 1:
            return False
        nums.sort()
        sum0 = sum(nums)
        print(sum0)
        if sum0 & 0x01 != 0:
            return False
        else:
            r = self.partitionRecursive(nums, 0, 0, (sum0 >> 1))
            return True if r == 0 else False

    def partitionRecursive(self, nums, edIndex, lastValue, halfSum):
        lastValue += nums[edIndex]
        if lastValue > halfSum:
            return 1
        elif lastValue == halfSum:
            return 0
        else:
            if edIndex == len(nums) - 1:
                return -1
            res = False
            for i in range(edIndex, len(nums) - 1):
                res = self.partitionRecursive(nums, i + 1, lastValue, halfSum)
                if res == 1:
                    continue
                elif res == 0:
                    return 0
                else:
                    continue
            return -1

if __name__ == "__main__":
    s = Solution()
    nums = [1, 5, 11, 5]
    nums = [2, 2, 3, 5]
    nums = [1,2,3,4,5,6,7]
    r = s.canPartition(nums)
    print(r)



