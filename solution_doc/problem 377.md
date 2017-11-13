## 377. Combination Sum IV

Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:

```
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
```


Note that different sequences are counted as different combinations.

Therefore the output is 7.
- Follow up:
- What if negative numbers are allowed in the given array?
- How does it change the problem?
- What limitation we need to add to the question to allow negative numbers?
- 


#### tips
这个和之前的几种组合题目不同的是 之前的只是组合 这个是排列 所以这个题目中 (1,2,1) (2,1,1)是两种不同的结果 所以 用dp是一种更好的方法
之前的方法不用dp是因为 要保证递归链上拆分的加数 是逐渐减小的 所以使用dp去记录结果可能导致结果的不完整。 但是这个组合题目如果要使用dp的话 记得一定要保证
fun(a) 是组成a的所有的结果 这样才能保证正确性

另外这个题目需要注意的是 一个组合如果递归到最后发现他不能组成target 需要把这个状态沿着递归链返回回来,否则后面的因为找到了结果 其实是没找到 这样会导致结果偏多

可以返回一个特殊的值作为状态以供上层的递归进行处理

注意吧组合和排列区别开

#### mycode

```
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
```



