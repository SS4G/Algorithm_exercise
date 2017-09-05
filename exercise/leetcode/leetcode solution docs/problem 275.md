## 275. H-Index II
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

#### tips
其他的介绍见 leetcode274 
既然题目 中要求使用logN的方法 那么就是先排序 然后用二分 

说明 二分查找其实不仅仅限于淡出的查找 可以根据特定的条件来对区间进行不断的的缩小 比如查找极值这种问题

#### mycode

```
class Solution(object):
    def hIndex(self, citations):
        """
        :type citations: List[int]
        :rtype: int
        """
        citations.reverse()
        lo = 0
        hi = len(citations) - 1
        while lo <= hi:
            mid = (lo + hi) >> 1
            if citations[mid] >= mid + 1:
                lo = mid + 1
            else:
                hi = mid - 1
        return hi + 1
```
