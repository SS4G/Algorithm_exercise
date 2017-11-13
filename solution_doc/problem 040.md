## 40. Combination Sum II

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 

```
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

#### tips
这个和leet039类似 但是每个加数的个数是给定的 所以就可以在leet039的基础上 加一个数量的限制条件， 用一个字典记录每个加数还可以被拆分进去的次数

#### mycode

```
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
```
