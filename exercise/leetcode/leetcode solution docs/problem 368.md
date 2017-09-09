## 368. Largest Divisible Subset
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:


```
nums: [1,2,3]
```

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:


```
nums: [1,2,4,8]
```


```
Result: [1,2,4,8]
```
#### tips

可以先对数组排个序 升序

如果后面的能够整除当前集合中最后一个那么吧这个元素加入以后 这个集合仍然满足任意两个数都可以整除的要求 这个题目可以使用类似于LIS的求法 只不过LIS对于子序列的限制条件是不减少 这个的限制条件是 可除 仅此而已 注意这个题目要求我们输出的是序列而不是长度 所以需要用一个专门的记录序列来记录最长的序列

#### mycode
```
class Solution(object):
    def largestDivisibleSubset(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        if len(nums) <= 1:
            return nums
        nums.sort()
        record = [1, ] * len(nums)
        indexRec = [[i, ] for i in range(len(nums))]

        record[0] = 1
        indexRec[0] = [0, ]
        for i in range(1, len(nums)):
            lastIndex = -1
            for j in range(i):
                if nums[i] % nums[j] == 0:
                    if record[j] + 1 > record[i]:
                        record[i] = record[j] + 1
                        lastIndex = j
            if lastIndex != -1:
                indexRec[i] = indexRec[lastIndex][:]
                indexRec[i].append(i)

        curMaxLen = 0
        curList = []
        for i in range(len(record)):
            if record[i] > curMaxLen:
                curMaxLen = record[i]
                curList = indexRec[i]
        return [nums[i] for i in curList]
```
