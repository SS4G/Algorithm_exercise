## 209. Minimum Size Subarray Sum

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

#### tips
这个就是求 >= s 的情况下 最短的子串 注意不是子序列 子串必须是连续的

这类问题的典型的解法就是使用两个指针 如果当前子序列的和小于s 就把前面的指针向前移动 否则 把后面的指针向前移动 每次刚刚符合 >=s的时候就记录下当前的 长度 这种做法 记录下当前长度的时候相当于是获取了 以每个元素为起始点的时候满足条件的最小长度，最终的结果必然产生在他们之中。

程序中最应该注意的bug就是在前面的指针 移动到序列末尾的情况 如果不处理这个边界可能会出问题

#### mycode

```
class Solution(object):
    def minSubArrayLen(self, s, nums):
        """
        :type s: int
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        if len(nums) == 1:
            return 0 if nums[0] >= s else 1
        lo = 0
        hi = 1
        curSum = nums[lo]
        curMinLen = 0x7fffffff
        foundFlag = False
        while hi < len(nums):
            if curSum < s:
                curSum += nums[hi]
                hi += 1
                if hi == len(nums) and curSum >= s:
                    foundFlag = True
                    while curSum - nums[lo] >= s:
                        curSum -= nums[lo]
                        lo += 1
                    curMinLen = min(hi - lo, curMinLen)
            else:
                foundFlag = True
                curMinLen = min(hi - lo, curMinLen)
                curSum -= nums[lo]
                lo += 1
        return curMinLen if foundFlag else 0
```
