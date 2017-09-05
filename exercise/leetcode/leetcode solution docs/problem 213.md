## 213. House Robber II

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

#### tips
这个题目 还是继承了 leet198 的主要思想 动态规划的方式 还是依据前面的一家商店抢或者不抢这个状态来完成这个动态规划链
只不过在环状路上要分三种情况来讨论 就是 只不抢第0家 只不抢最后一家 第0家和最后一家都不抢 最大的情况必然出现在这三种情况之间 最后取最大值就好了

#### mycode

```
class Solution(object):
    def rob(self, nums):
        if len(nums) == 0:
            return 0
        if len(nums) == 1:
            return nums[0]
        if len(nums) <= 3:
            return max(nums)

        # n - 1 not robbed
        preNot0 = 0
        preYes0 = nums[0]
        for i in range(1, len(nums) - 1):
            curNot0 = max(preNot0, preYes0)
            curYes0 = preNot0 + nums[i]
            preNot0 = curNot0
            preYes0 = curYes0

        max0 = max(preNot0, preYes0)

        preNot1 = 0
        preYes1 = nums[1]
        for i in range(2, len(nums)):
            curNot1 = max(preNot1, preYes1)
            curYes1 = preNot1 + nums[i]
            preNot1 = curNot1
            preYes1 = curYes1

        max1 = max(preNot1, preYes1)

        preNot2 = 0
        preYes2 = nums[1]
        for i in range(2, len(nums) - 1):
            curNot2 = max(preNot2, preYes2)
            curYes2 = preNot2 + nums[i]
            preNot2 = curNot2
            preYes2 = curYes2

        max2 = max(preNot2, preYes2)
        return max([max1, max2, max0])
```
