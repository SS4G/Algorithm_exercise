class Solution(object):
    def subsetsWithDup(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        numSet = set(nums)
        numStatistic = {}
        for i in numSet:
            numStatistic[i] = 0
        for i in nums:
            numStatistic[i] += 1
        nonDupNumsList = list(numSet)
        nonDupNumsList.sort()
        nonDupRes = []
        for i in range(0, 2**len(nonDupNumsList)):
            nonDupRes.append(self.reflect(nonDupNumsList, i))
        finalres = []
        for li in nonDupRes:
            finalres.extend(self.multiExt(li, numStatistic))
        # finalres.append([])
        return finalres

    def multiExt(self, nonDupList, numStatistic):
        lastresult = [nonDupList.copy(), ]
        for i in nonDupList:
            thisIntegerExt = lastresult.copy()
            for j in range(2, numStatistic[i]+1):
                for k in lastresult:
                    thisIntegerExt.append(self.extendRes(list0=k, integer=i, extTimes=j))
            lastresult = thisIntegerExt
        return lastresult

    def reflect(self, nums, integer):
        res = []
        mask = 0x01
        for i in range(len(nums)):
            if mask & integer != 0:
                res.append(nums[i])
            mask <<= 1
        return res

    def extendRes(self, list0, integer, extTimes):
        assert extTimes > 1, "code bug!!!"
        cp = None
        if extTimes > 1:
            cp = list0.copy()
            for i in range(1, extTimes):
                cp.append(integer)
        return cp


if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 2, 3, 3, 3]
    k = 0
    for i in s.subsetsWithDup(arr):
        print(i, k)
        k += 1