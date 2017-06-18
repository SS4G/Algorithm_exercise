## 90. Subsets II Add to List

Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:


```
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

#### tips
先按照 不重复的元素来产生一系列的子集
然后根据每个元素重复的多少来进行扩展

如 
原始子集 [1,2,3]
实际2出现了3次
扩展为[1,2,3] [1,2,2,3] [1,2,2,2,3]
同理将而扩展后的每一个结果 根据后面的数字出现的次数再次扩展 

#### mycode
```
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
        lastresult = [nonDupList[:], ]
        for i in nonDupList:
            thisIntegerExt = lastresult[:]
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
            cp = list0[:]
            for i in range(1, extTimes):
                cp.append(integer)
        return cp
```
