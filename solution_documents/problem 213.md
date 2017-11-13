## 213. House Robber II

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

#### tips
还有一种方法是 按照抢不抢第0家 
按照198的方法算dphas0 这个中的值 是可能抢了第0家 
然后再计算dphas1 这个中的值肯定不包括第0家
如果dphas0中的值其实没有包括0 那么将会和dphas1 的值一模一样
所以在决定到最后的一个商店的dp值时
如果使用dphas0 就绝对不要抢最后一个
否则要抢就使用dphas1 
然后取最大值。这样就涵盖了所有可能的情况。 如果dphas0 和dphas1 相等 这样也可以得到正确的结果 当然这个结果里面也包含了0, 1都不抢的情况 这个情况是在dphas1中出现

note 动态规划的思想就是 真实的最优情况能不能被涵盖 如果能够涵盖 那么求出来的就是最优的 否则是错的。把一个问题划分为几个互斥的子问题 求出每个互斥子问题的最优 然后最后规约的问题就是最优

#### mycode

```Java
class Leet213x {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        int[] dpHas0 = new int[nums.length];
        int[] dpHas1 = new int[nums.length];
        dpHas0[0] = nums[0];
        dpHas0[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        //dpHas1[0] = nums[0];
        dpHas1[1] = nums[1];
        dpHas1[2] = nums[1] > nums[2] ? nums[1] : nums[2];

        for (int i = 2; i < nums.length; i++) {
            dpHas0[i] = Math.max(dpHas0[i - 1], dpHas0[i - 2] + nums[i]);
        }

        for (int i = 3; i < nums.length; i++) {
            dpHas1[i] = Math.max(dpHas1[i - 1], dpHas1[i - 2] + nums[i]);
        }
        return Math.max(dpHas0[nums.length - 2], dpHas1[nums.length - 1]);
    }
}

```


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
