## 56. Merge Intervals Add to List

Given a collection of intervals, merge all overlapping intervals.

For example,

```
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
```

#### tips
首先根据其实索引对区间排序 然后从前到后使用堆栈两两合并 如果合并成一个新的区间 就将旧的区间从堆栈中弹出 然后将合并后的新区间放入堆栈 下次合并时从栈顶 选取 
合并失败 就直接将新的区间放入堆栈 最后堆栈中的区间即为结果
#### mycode
```Python
class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[Interval]
        """
        if len(intervals) == 0:
            return []

        intervals = sorted(intervals, key=operator.attrgetter('start'))
        stack = []
        stack.append(intervals[0])
        for i in range(1, len(intervals)):
            if len(stack) > 0:
                res = self.mergeTwo(stack[-1], intervals[i])
                if len(res) == 2:
                    stack.append(intervals[i])
                else:
                    stack.pop()
                    stack.append(res[0])
            else:
                stack.append(intervals[i])

        return stack

    def mergeTwo(self, intervalA, intervalB):
        """

        :param intervalA: intervals
        :param intervalB: intervals
        :return:
        """
        if intervalA.start > intervalB.start:
            intervalA, intervalB = intervalB, intervalA
        # assure sa <= sb
        if intervalB.start > intervalA.end:
            return [intervalA, intervalB]
        else:
            return [Interval(s=min(intervalA.start, intervalB.start), e=max(intervalA.end, intervalB.end)), ]
```
