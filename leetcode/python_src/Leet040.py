class Solution(object):
    def combinationSum2(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        maxVal = max(candidates)
        candDict = {}
        for k in candidates:
            if k not in candDict:
                candDict[k] = 1
            else:
                candDict[k] += 1

        return self.combinationSumRecursive(candDict, target, maxVal)

    def combinationSumRecursive(self, candDict, target, upLim):
        if target < 0:
            return []
        elif target == 0:
            return [[], ]
        else:
            finalRes = []
            for i in candDict:  # the list is acsending
                if i <= upLim and candDict[i] > 0:
                    candDict[i] -= 1
                    tmp = self.combinationSumRecursive(candDict, target - i, i)
                    candDict[i] += 1
                    for j in tmp:
                        j.append(i)
                        finalRes.append(j)
            res = []
            for i in finalRes:
                res.append(i[:])
            return res

if __name__ == "__main__":
    s = Solution()
    cand = [1, 1, 2, 2]
    print(s.combinationSum2(cand, 4))

