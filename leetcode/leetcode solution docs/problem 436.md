## 436. Find Right Interval

Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
- You may assume the interval's end point is always bigger than its start point.
- You may assume none of these intervals have the same start point.

Example 1:

```
Input: [ [1,2] ]

Output: [-1]
```


Explanation: There is only one interval in the collection, so it outputs -1.


Example 2:

```
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]
```


Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.


Example 3:

```
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]
```


Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.

#### tips
 如果要查找 (a,b) 右边的区间 (c,d) 那么一定要有 c>=b 
 所以 可以用一个字典来记录搜有区间 以他们的起始点作为键 
 
 然后用二分法 在这些键中查找 第一个大于等于b的键 如果找到即可
 
 

#### mycode

```
class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e


class Solution(object):
    def findRightInterval(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[int]
        """
        newIntervals = [(intervals[i].start, intervals[i].end, i) for i in range(len(intervals))]
        newIntervals.sort()
        intervalDict = {}
        for i in newIntervals:
            intervalDict[i[0]] = i

        result = [-1, ]*len(intervals)
        startIndexs = list(intervalDict.keys())
        startIndexs.sort()
        j = 0
        for i in intervals:
            ed = i.end
            edIndex = self.findNextGreater(startIndexs, ed)
            if edIndex < len(startIndexs):
                result[j] = intervalDict[startIndexs[edIndex]][2]
                # min([interval[2] for interval in intervalDict[edIndex]])
            else:
                result[j] = -1
            j += 1
        return result

    def findNextGreater(self, arr, target):
        """
        assume no duplicates in arr
        :param arr:
        :param target:
        :return:
        """
        lo = 0
        hi = len(arr) - 1
        while lo <= hi:
            mid = (lo + hi) >> 1
            if arr[mid] < target:
                lo = mid + 1
            elif arr[mid] == target:
                return mid
            else:
                hi = mid - 1
        return lo
```
