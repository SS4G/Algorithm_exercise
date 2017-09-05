class Solution(object):
    def combinationSum4(self, nums, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        if len(nums) == 0:
            return 0
        nums.sort()
        resDict = {}
        self.combinationSumRecursive(nums, target, resDict)
        return resDict[target]

    def combinationSumRecursive(self, candList, target, resDict):
        if target in resDict:
            return resDict[target]
        elif target < 0:
            return -1
        elif target == 0:
            return 0
        else:
            cur = 0
            allNegFlag = True
            for i in candList:
                res = self.combinationSumRecursive(candList, target - i, resDict)
                if res == -1:
                    pass
                elif res == 0:
                    allNegFlag = False
                    cur += 1
                else:
                    cur += res
                    allNegFlag = False
            cur = cur if not allNegFlag else -1
            resDict[target] = cur
            return cur

if __name__ == "__main__":
    s = Solution()
    #print(s.calucCombineNationNum([1,2,1,2,1,3]))
    nums = [3, 4, 5, 6, 7, 8, 9, 10]
    target = 10
    print(s.combinationSum4(nums, target))