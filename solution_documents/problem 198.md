## 198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

#### update
#### tips
这个动态规划的套路不是一定的 之前去计算前多少个最大的 抢劫值是多少还要记录最大的抢劫值最后抢的一家是谁这样会很麻烦
所以 可以用另一种思路去考虑问题

每一个房子的状态只有两种 我们可以选择抢或者不抢 可以用一个二维数组记录这两种不同决策下的最大值
dp[i][0] 代表不抢这个房子的最大值 dp[i][1] 代表抢这个房子的最大值

状态的转移依赖于下面的计算式 如果抢 那么前一个房子必然是不抢
如果不抢 前一个房子可抢可不抢
```
dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
dp[i][1] = dp[i - 1][0] + nums[i]
```
最后代码如下 时间复杂度o(n) 空间复杂度o(n)

#### mycode

```
class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        dp = [[None, None] for i in range(len(nums) + 1)]
        #  dp[i][0] maxium val if we don't rob this house
        #  dp[i][1] maxium val if we rob this house
        dp[0][0] = 0
        dp[0][1] = nums[0]
        for i in range(1, len(nums)):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
            dp[i][1] = dp[i - 1][0] + nums[i]
        return max(dp[len(nums) - 1][0], dp[len(nums) - 1][1])
```


可以对上述的代码进行优化 因为dp只会直接使用前一次的结果 所以没有必要全部吧之前的结果记录下俩 用两个pre变量 记录上一次的结果即可


#### mycode optimized
time-O(n) space-O(1)

```
class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        preNot = 0
        preYes = nums[0]
        for i in range(1, len(nums)):
            curNot = max(preNot, preYes)
            curYes = preNot + nums[i]
            preNot = curNot
            preYes = curYes

        return max(preNot, preYes)
```



----------------------
 old

#### tips
这个题目也是动态规划的题目 他的子问题题是 前N家点的钱数给定 怎么抢劫最划算 
如果要求N 则 和 N-1 N-2 的子问题有关 而且要考虑抢劫时 N-1中最后一个被抢的店是不是 N-1 这个会影响到后面的决策 如果是 就说明 求N的情况下 如果依赖于N-1 那么 N是不可以被抢的 为了保证结果的正确性 我们还要对N-2进行探讨 道理是一样的 每次计算中间结果的过程中可以使用一个字典来进行记录中间的结果方便查询


#### mycode
```
class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        recDict = {}
        res = self.robRecursive(nums, len(nums) - 1, recDict)[0]
        return res

    def robRecursive(self, nums, finalIndex, recDict):
        if finalIndex in recDict:
            return recDict[finalIndex]
        if finalIndex == 0:
            recDict[finalIndex] = (nums[0], [0])
            return recDict[finalIndex]
        elif finalIndex == 1:
            if nums[0] < nums[1]:
                recDict[finalIndex] = (nums[1], [1])
                return recDict[finalIndex]
            elif nums[0] > nums[1]:
                recDict[finalIndex] = (nums[0], [0])
                return recDict[finalIndex]
            else:
                recDict[finalIndex] = (nums[1], [1, 0])
                return recDict[finalIndex]
        else:
            res0, lastIndex0 = self.robRecursive(nums, finalIndex - 1, recDict)
            res1, lastIndex1 = self.robRecursive(nums, finalIndex - 2, recDict)
            if len(lastIndex0) == 2:
                recDict[finalIndex] = (res0 + nums[finalIndex], [finalIndex,])
                return recDict[finalIndex]
            else:
                if lastIndex0[0] == finalIndex - 1:
                    tmpRes0, tmpIndex0 = res0, [finalIndex - 1, ]
                else:
                    tmpRes0, tmpIndex0 = res0 + nums[finalIndex], [finalIndex, ]

                tmpRes1, tmpIndex1 = res1 + nums[finalIndex], [finalIndex, ]
                if tmpRes1 > tmpRes0:
                    tmpRes0 = tmpRes1
                    tmpIndex0 = tmpIndex1
                elif tmpRes0 == tmpRes1:
                    if lastIndex0[0] == finalIndex - 1:
                        tmpIndex0.append(finalIndex - 1)

                recDict[finalIndex] = (tmpRes0,  tmpIndex0)
            return recDict[finalIndex]
```
