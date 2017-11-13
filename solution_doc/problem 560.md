## 560. Subarray Sum Equals K

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

```
Input:nums = [1,1,1], k = 2
```
Output: 2
Note:
- The length of the array is in range [1, 20,000].
- The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

#### tips
还是同样的方法 见Leet525 523 presum + hashmap

#### mycode

```
class Solution(object):
    def subarraySum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        sumRec = [-1, ] * len(nums)
        resDict = {}
        resDict[0] = {-1}
        for i in range(len(nums)):
            if i == 0:
                sumRec[i] = nums[0]
            else:
                sumRec[i] = nums[i] + sumRec[i - 1]
            if sumRec[i] not in resDict:
                resDict[sumRec[i]] = {i}
            else:
                resDict[sumRec[i]].add(i)
        cnt = 0
        for sumx in resDict:
            if sumx - k in resDict:
                for j in resDict[sumx]:
                    for p in resDict[sumx - k]:
                        if j > p:
                            cnt += 1
        return cnt
```
