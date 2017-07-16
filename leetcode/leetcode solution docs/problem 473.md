## 473. Matchsticks to Square

Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:

```
Input: [1,1,2,2,2]
Output: true
```


Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:

```
Input: [3,3,3,3,4]
Output: false
```


Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

#### tips
这个题目叫的重点在于 使用dfs时 是如何完成的 是想象成按照顺序拿出一个火柴 找正方形的四条边之一放下去 然后按照这个套路进行 dfs 回溯 这个比一根一根没哟目的的尝试 能够刚快的完成 因为这个的重复计算的过程 比随便拿一根就放 会出现交替重叠的现象的回溯方法要快很多

#### mycode

```
class Solution(object):
    def makesquare(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) == 0:
            return False
        totalLength = sum(nums)
        if totalLength % 4 != 0:
            return False
        else:
            sideLen = totalLength >> 2
        nums.sort(reverse=True)
        return self.dfsRecursive(nums, [0, ] * 4, 0, sideLen)

    def dfsRecursive(self, nums, sums, index, target):
        if index == len(nums):
            if sums[0] == target and sums[1] == target and sums[2] == target and sums[3] == target:
                return True
            else:
                return False

        for i in range(4):
            if sums[i] + nums[index] > target:
                continue
            sums[i] += nums[index]
            if self.dfsRecursive(nums, sums, index + 1, target):
                return True
            sums[i] -= nums[index]
        return False
```
