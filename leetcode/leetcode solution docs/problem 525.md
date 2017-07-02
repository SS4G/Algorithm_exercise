## 525. Contiguous Array

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:

```
Input: [0,1]
Output: 2
```

Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:

```
Input: [0,1,0]
Output: 2
```

Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.

#### tips
吧题目中的0 换成-1 则表示求这个序列中 最长的和为0的子序列 同样可以使用 presum + hashmap的解决方案 即先对每个位置求出这个位置到头部的累加和， 然后 用hashmap记录每个累加和的位置 根据这个题目 就是要在相同的累加和中 找出这个累加和中记录的坐标的最大距离 具体见代码

#### mycode


```
class Solution(object):
    def findMaxLength(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        for i in range(len(nums)):
            if nums[i] == 0:
                nums[i] = -1

        sumRec = [-1, ] * len(nums)
        resDict = {}
        resDict[0] = [-1]
        for i in range(len(nums)):
            if i == 0:
                sumRec[i] = nums[0]
            else:
                sumRec[i] = nums[i] + sumRec[i - 1]
            if sumRec[i] not in resDict:
                resDict[sumRec[i]] = [i, ]
            else:
                resDict[sumRec[i]].append(i)
        maxLen = 0
        for sumx in resDict:
            if len(resDict[sumx]) >= 2:
                maxLen = max(maxLen, resDict[sumx][-1] - resDict[sumx][0])
        return maxLen
```
