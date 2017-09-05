## 416. Partition Equal Subset Sum
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:


```
Input: [1, 5, 11, 5]
```

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:


```
Input: [1, 2, 3, 5]
```

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

#### tips
本质上是一个01背包问题 我们就是要找 前i个数字中 能不能有数字组成totalSum/2

然后使用0-1背包的典型解法 求出前i个元素里面 能否组成0 ~ totalSum/2 每个元素的状态有两种 装或者不装


```
dp[index][value]
dp[n][i] = dp[n - 1][i] or dp[n - 1][i - nums[n]]
```
虽然有的结果并不会用到

注意的是每次求dp都是在上次的dp基础上完成的

#### mycode

```
class Solution(object):
    def canPartition(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) <= 1:
            return False
        nums.sort()
        sum0 = sum(nums)
        if sum0 & 0x01 != 0:
            return False
        else:
            halfSum = sum0 >> 1
            dp = [[False, ] * (halfSum + 1) for i in range(len(nums))]

            for i in range(len(nums)):
                if nums[i] <= halfSum:
                    dp[i][nums[i]] = True
                dp[i][0] = True

            for n in range(1, len(nums)):
                i = halfSum
                while i >= nums[n]:
                    dp[n][i] = dp[n - 1][i] or dp[n - 1][i - nums[n]]
                    i -= 1
            return dp[len(nums) - 1][halfSum]
```
