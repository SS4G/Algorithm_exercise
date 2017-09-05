## 47. Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:

```
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```
#### tips
使用一个一个位置放的方法 来从后面的集合中选取不同的数字， 用回溯法即可

选对完成组合的方法很重要 具体要看代码

#### mycode


```
class Solution(object):
    def permuteUnique(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        dicts = {}
        for i in nums:
            if i not in dicts:
                dicts[i] = 1
            else:
                dicts[i] += 1
        res = []
        stack = []
        self.permuteUniqueRecurive(nums, 0, len(nums), dicts, stack, res)
        return res

    def permuteUniqueRecurive(self, nums, start, end, dicts, stack, res):
        if end - start == 1:
            for j in filter(lambda key: dicts[key] > 0, dicts.keys()):
                stack.append(j)
            res.append(stack[:])
            stack.pop()
        else:
            for i in set(filter(lambda key: dicts[key] > 0, dicts.keys())):
                stack.append(i)
                dicts[i] -= 1
                self.permuteUniqueRecurive(nums, start + 1, end, dicts, stack, res)
                stack.pop()
                dicts[i] += 1
```
