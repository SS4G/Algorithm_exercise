## 539. Minimum Time Difference Add to List

Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

Example 1:

```
Input: ["23:59","00:00"]
Output: 1
```

- Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.

#### tips
把全部的时间换算成相对于0点的分钟数
然后想象成一个环 然后 求出环上相邻两点间的最短距离即可

#### mycode

```
class Solution(object):
    def findMinDifference(self, timePoints):
        """
        :type timePoints: List[str]
        :rtype: int
        """
        times = [self.toAbsolutSeconds(time) for time in timePoints]
        times.sort()
        return min([24*60 - times[-1] + times[0], ]+[times[i] - times[i - 1] for i in range(1, len(times))])

    def toAbsolutSeconds(self, timestr):
        l = [int(s) for s in timestr.split(":")]
        return l[0]*60 + l[1]
```
