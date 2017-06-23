class Solution(object):
    def checkSubarraySum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        if len(nums) < 2:
            return False
        sumRec = [-1, ] * len(nums)
        for i in range(len(nums)):
            if i == 0:
                sumRec[i] = nums[0]
            else:
                sumRec[i] = nums[i] + sumRec[i - 1]
        if k == 0:
            return True if sumRec[-1] == 0 else False

        markDict = {}
        for i in range(len(sumRec)):
            res = sumRec[i] % abs(k)
            if res == 0 and i != 0:
                return True
            elif res in markDict:
                return True
            else:
                markDict[res] = 1
        return False


if __name__ == "__main__":
    s = Solution()
    arr = [5, 2, 4]
    k = 5
    print(s.checkSubarraySum(arr, k))