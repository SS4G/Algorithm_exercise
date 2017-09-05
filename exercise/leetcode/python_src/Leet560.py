class Solution(object):
    def subarraySum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        sumRec = [-1, ] * len(nums)
        resDict = {}
        resDict[0] = {-1}
        for i in range(len(nums)):
            if i == 0:
                sumRec[i] = nums[0]
            else:
                sumRec[i] = nums[i] + sumRec[i - 1]
            if sumRec[i] not in resDict:
                resDict[sumRec[i]] = {i}
            else:
                resDict[sumRec[i]].add(i)
        cnt = 0
        for sumx in resDict:
            if sumx - k in resDict:
                for j in resDict[sumx]:
                    for p in resDict[sumx - k]:
                        if j > p:
                            cnt += 1
        return cnt

if __name__ == "__main__":
    s = Solution()
    nums = [1, 1, 1]
    k = 2
    print(s.subarraySum(nums, k))