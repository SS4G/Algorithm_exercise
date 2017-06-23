class Solution(object):
    def findMaxLength(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        for i in range(len(nums)):
            if nums[i] == 0:
                nums[i] = -1

        sumRec = [-1, ] * len(nums)
        resDict = {}
        resDict[0] = [-1]
        for i in range(len(nums)):
            if i == 0:
                sumRec[i] = nums[0]
            else:
                sumRec[i] = nums[i] + sumRec[i - 1]
            if sumRec[i] not in resDict:
                resDict[sumRec[i]] = [i, ]
            else:
                resDict[sumRec[i]].append(i)
        maxLen = 0
        for sumx in resDict:
            if len(resDict[sumx]) >= 2:
                maxLen = max(maxLen, resDict[sumx][-1] - resDict[sumx][0])
        return maxLen

if __name__ == "__main__":
    s = Solution()
    nums = [0,1,0,0,0,1,1,1]
    print(s.findMaxLength(nums))