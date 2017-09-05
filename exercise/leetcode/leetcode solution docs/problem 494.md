## 494. Target Sum
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 


```
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
```


There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
- The length of the given array is positive and will not exceed 20.
- The sum of elements in the given array will not exceed 1000.
- Your output answer is guaranteed to be fitted in a 32-bit integer.
- 


#### tips
这个问题也算是一个动态规划吧 可以用递归来解决 可以假设当前的数字前面的符号是正好或者是负号 然后去查找剩余要求的结果在前面的序列中是否可行 中间可以使用一个字典去做记录即可


PS：这里主要要注意0的情况 尤其是第一个位置上的0 也有两种可能 +0 -0
#### mycode

```
class Solution(object):
    def findTargetSumWays(self, nums, S):
        """
        :type nums: List[int]
        :type S: int
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        dict0 = {}
        res = self.findTargetRecursive(nums, len(nums) - 1, S, dict0)
        return res

    def findTargetRecursive(self, nums, startIndex, targetSum, recDict):
        if (startIndex, targetSum) in recDict:
            return recDict[(startIndex, targetSum)]
        if startIndex == 0:
            if targetSum == 0 and nums[0] == 0:
                res = 2
            elif nums[0] == targetSum or nums[0] == -targetSum:
                res = 1
            else:
                res = 0
        else:
            a = self.findTargetRecursive(nums, startIndex - 1, targetSum - nums[startIndex], recDict)
            b = self.findTargetRecursive(nums, startIndex - 1, targetSum + nums[startIndex], recDict)
            res = a + b
        recDict[(startIndex, targetSum)] = res
        return res
```

