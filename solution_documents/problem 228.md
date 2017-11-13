## 228. Summary Ranges

Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

#### tips
这个题目是个水题就是 用一个二元的状态机来找区间 只要注意在 查找到数组最后的地方 需要确定一个状态是否完成了 否则会丢掉最后的一个区间

#### mycode

```
class Solution(object):
    def summaryRanges(self, nums):
        """
        :type nums: List[int]
        :rtype: List[str]
        """
        START_FOUND = 0
        END_FOUND = 1
        state = END_FOUND
        res = []
        rangePtr = -1
        i = 0
        while i < len(nums):
            if state == END_FOUND:
                st = nums[i]
                rangePtr = nums[i]
                i += 1
                state = START_FOUND
            else:
                if nums[i] == rangePtr + 1:
                    rangePtr = nums[i]
                    i += 1
                else:
                    res.append((st, rangePtr))
                    state = END_FOUND
        if state != END_FOUND:
            res.append((st, rangePtr))
        finalRes = []
        for r in res:
            if r[0] == r[1]:
                finalRes.append(str(r[0]))
            else:
                finalRes.append(str(r[0])+"->"+str(r[1]))
        return finalRes
```
