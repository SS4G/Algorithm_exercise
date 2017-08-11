class Solution:
    def findErrorNums(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        errorSum = 0
        dict0 = set([])
        errorNum = -1
        lastIdx = 0
        for i in range(len(nums)):
            errorSum += nums[i]
            if nums[i] in dict0:
                errorNum = nums[i]
            dict0.add(nums[i])
            lastIdx = i
        lastIdx += 1
        stdSum = ((lastIdx + 1) * lastIdx) >> 1
        return list((errorNum, stdSum + errorNum - errorSum))

if __name__ == "__main__":
    s = Solution()
    print(s.findErrorNums([1,2,2,4]))