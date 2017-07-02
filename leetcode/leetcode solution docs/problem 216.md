## 216. Combination Sum III

Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

#### mycode
在leetcode 040的基础上 吧候选数字的个数全部定为1 也就是说 每个候选数字只能选一个， 然后再把长度不符合要求的结果过滤掉即可

#### tips
```
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
```
