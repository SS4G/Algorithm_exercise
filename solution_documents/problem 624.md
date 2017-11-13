## 624. Maximum Distance in Arrays

Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 

```
[[1,2,3],
 [4,5],
 [1,2,3]]
```

Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.


Note:
- Each given array will have at least 1 number. There will be at least two non-empty arrays.
- The total number of the integers in all the m arrays will be in the range of [2, 10000].
- The integers in the m arrays will be in the range of [-10000, 10000].

#### tips
最小的值肯定出现在某个区间的头部
最大的值肯定出现在某个区间的尾部
并且保证这对大小值不是属于同一个区间的
所以 我们遍历所有区间 保存 第一大和第二大 最大值
保存第一小和第二小最小值 并且记录他们是属于哪个区间的

如果 第一大和第一小不是一个区间的那么结果就是他们差的绝对值
否则 结果只可能产生在 第一大和第二小 或者二第一小和第二大之间

#### mycode

```
class Solution(object):
    def maxDistance(self, arrays):
        """
        :type arrays: List[List[int]]
        :rtype: int
        """
        firstMax = (-0xffffffff, -1)
        secondMax = (-0xffffffff, -1)
        firstMin = (0xffffffff, -1)
        secondMin = (0xffffffff, -1)
        flag = False
        arrid = 0
        for i in arrays:
            if len(i) > 0:
                flag = True
                maxThis = i[-1]
                minThis = i[0]
                if maxThis >= firstMax[0]:
                    secondMax = firstMax
                    firstMax = (maxThis, arrid)
                elif secondMax[0] <= maxThis < firstMax[0]:
                    secondMax = (maxThis, arrid)
                else:
                    pass
                if minThis <= firstMin[0]:
                    secondMin = firstMin
                    firstMin = (minThis, arrid)
                elif secondMin[0] >= minThis > firstMin[0]:
                    secondMin = (minThis, arrid)
                else:
                    pass
            arrid += 1

        if firstMax[1] != firstMin[1]:
            return abs(firstMax[0] - firstMin[0])
        else:
            return max(abs(firstMax[0] - secondMin[0]), abs(secondMax[0] - firstMin[0]))
```
