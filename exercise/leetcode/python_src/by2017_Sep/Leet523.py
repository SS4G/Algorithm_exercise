class Solution(object):
    def checkSubarraySum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        if len(nums) < 2:
            return False
        if k != 0:
            sumRec = [-1, ] * len(nums)
            for i in range(len(nums)):
                if i == 0:
                    sumRec[i] = nums[0]
                else:
                    sumRec[i] = nums[i] + sumRec[i - 1]
            #print(sumRec)
            for st in range(0, len(nums) - 1):

                for i in range(st + 1, len(nums)):
                    if abs(sumRec[i]) % abs(k) == 0:
                        return True
                    else:
                        sumRec[i] -= nums[st]
            return False
        else:
            sumRec = [-1, ] * len(nums)
            for i in range(len(nums)):
                if i == 0:
                    sumRec[i] = nums[0]
                else:
                    sumRec[i] = nums[i] + sumRec[i - 1]
            #print(sumRec)
            for st in range(0, len(nums) - 1):

                for i in range(st + 1, len(nums)):
                    if sumRec[i] == 0:
                        return True
                    else:
                        sumRec[i] -= nums[st]
            return False

if __name__ == "__main__":
    s = Solution()
    arr = [0, 0, 0, 0, 0]
    k = 0
    print(s.checkSubarraySum(arr, k))