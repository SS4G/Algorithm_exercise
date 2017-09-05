## 39. Combination Sum

Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 

```
[
  [7],
  [2, 2, 3]
]
```

#### tips
可以用回溯算法来递归求解 为了保证每次的分解出来的数字是逐渐减小的
比如 4 只能分解成 2+1+1 而不会中间出现结果 1+2+1 所以 要给每次递归设置一个上限 保证每次拆分出的值不大于这个上限 所以最后递归链上的加数只会逐渐减小 且整个的组合具有唯一性(需要注意与排列做出一个区分)

#### mycode


```
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
        # elif target in resDict:
        #     res = []
        #     for i in resDict[target]:
        #         res.append(i[:])
        #     return res
        else:
            finalRes = []
            for i in candList:  # the list is acsending
                if i <= upLim:
                    tmp = self.combinationSumRecursive(candList, target - i, resDict, i)
                    for j in tmp:
                        j.append(i)
                        finalRes.append(j)
            resDict[target] = finalRes
            res = []
            for i in resDict[target]:
                res.append(i[:])
            return res
```
