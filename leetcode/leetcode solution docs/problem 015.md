## 15. 3Sum

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:

```
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```
#### tips
使用set来做查找 可以实现o(lgn)的复杂度
和为0 可以分为 三种情况 ，三个0， 一正一负一个零 ， 两正一负，两负一正 可以先求出两个 然后去剩下的set中 查找 照的到就说明可以 在输出的时候可以用python的元组可以hash的特性 来保证每个结果的唯一性

#### mycode
```Python
class Solution(object):

    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """

        nums.sort()
        lastNeg = -1
        firstPos = -1
        for i in range(len(nums)):
            if nums[i] < 0:
                if i+1 >= len(nums) or nums[i + 1] >= 0:
                    lastNeg = i
            if nums[i] > 0:
                if i == 0 or nums[i - 1] <= 0:
                    firstPos = i
        zeroAmounts = firstPos - lastNeg - 1
        res = []
        if firstPos == 0 or lastNeg == len(nums) - 1:
            return []
        elif firstPos == -1 and lastNeg == -1:
            return [[0, 0, 0]] if len(nums) >= 3 else []


        posSet = set(nums[firstPos:])
        negSet = set(nums[:lastNeg + 1])
        if zeroAmounts > 0:  # at least 1 zero
            for pos in posSet:
                if -pos in negSet:
                    res.append((pos, 0, -pos))
            if zeroAmounts >= 3:
                res.append((0, 0, 0))

        for i in range(firstPos, len(nums)):
            for j in range(i + 1, len(nums)):
                posPart = nums[i] + nums[j]
                if -posPart in negSet:
                    res.append((nums[i], nums[j], -posPart))

        for i in range(0, lastNeg + 1):
            for j in range(i + 1, lastNeg + 1):
                negPart = nums[i] + nums[j]
                if -negPart in posSet:
                    res.append((nums[i], nums[j], -negPart))

        return [[item[0], item[1], item[2]] for item in set(res)]
```
