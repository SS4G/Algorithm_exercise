class Solution(object):
    def combinationSum(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        candidates.sort()
        resDict = {}
        res = self.combinationSumRecursive(candidates, target, resDict, upLim=candidates[-1])
        return res

    def combinationSumRecursive(self, candList, target, resDict, upLim):
        if target < 0:
            return []
        elif target == 0:
            return [[], ]
        else:
            finalRes = []
            for i in candList:  # the list is acsending
                if i <= upLim:
                    tmp = self.combinationSumRecursive(candList, target - i, resDict, i)
                    for j in tmp:
                        j.append(i)
                        finalRes.append(j)
            # resDict[target] = finalRes
            res = []
            for i in finalRes:
                res.append(i[:])
            return res

if __name__ == "__main__":
    s = Solution()
    cand = [1, 2]
    print(s.combinationSum(cand, 4))
