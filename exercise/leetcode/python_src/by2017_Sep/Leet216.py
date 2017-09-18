class Solution(object):
    def combinationSum3(self, k, n):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        candDict = {1: 1, 2: 1, 3: 1, 4: 1, 5: 1, 6: 1, 7: 1, 8: 1, 9: 1}

        res = self.combinationSumRecursive(candDict, n, 9)
        res = [p for p in res if len(p) == k]
        return res

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
    print(s.combinationSum3(3, 9))

