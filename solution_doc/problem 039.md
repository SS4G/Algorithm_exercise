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
这个题目可以使用更高效的动态规划来做 使用一个字典来记录 一个数字a 能够用候选数组组成的所有结果 如果要求target 候选字典为[a, b, c] 那么可以递归的求   
combination(taget-a)  
combination(taget-b)    
combination(taget-c)

然后把他们的结果分别和a, b, c 结合起来 

最后需要注意的是要保证结果的唯一性 所以应该保证尾部只填充比前面大的结果 这样保证了只有组合而没有排列

#### mycode

```
class Solution {
   public List<List<Integer>> combinationSum(int[] candidates, int target) {
        HashMap<Integer, List<List<Integer>>> record = new HashMap<>();
        return combineRecure(record, target, candidates);
    }

    private List<List<Integer>> combineRecure(HashMap<Integer, List<List<Integer>>> partResRecord, int target, int[] candidates) {
        if (partResRecord.containsKey(target))
            return partResRecord.get(target);
        else {
            List<List<Integer>> tmpSumSet = new ArrayList<>();
            for (int cand : candidates) {
                List<List<Integer>> lastSumSet;
                List<Integer> oneResult;
                if (target - cand > 0) {
                    //System.out.println(target + ":" + cand);
                    lastSumSet = combineRecure(partResRecord, target - cand, candidates);
                    //System.out.println(lastSumSet);
                    if (lastSumSet.size() > 0) {
                        //System.out.println("ss");
                        for (List<Integer> i : lastSumSet) {
                            if (i.get(i.size() - 1) <= cand) { //保证只有从小到大的一种排列
                                //System.out.println("ss");
                                oneResult = new ArrayList<>(i);
                                oneResult.add(cand);
                                tmpSumSet.add(oneResult);
                            }
                        }
                    }
                } else if (target - cand == 0) {
                    oneResult = new ArrayList<>();
                    oneResult.add(cand);
                    tmpSumSet.add(oneResult);
                }
            }
            partResRecord.put(target, tmpSumSet);
            return tmpSumSet;
        }
    }
}
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
