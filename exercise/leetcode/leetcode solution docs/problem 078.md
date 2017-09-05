## 78. Subsets Add to List

Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

```
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```
#### tips
这个题的每个元素只有两种状态， 存在于集合中和不存在于集合中，所以可以用 len(nums)个二进制位来表示所有的子集 
将这些二进制位看做是一个整数 假设有 32 位 那么 就从 0x00000000~ 0xffffffff 然后将每个二进制位进行映射 最后得到 所有可能的结果

当然本题也可以用回溯法去做 使用递归或者是用堆栈进行跟踪

#### mycode

```
class Solution(object):
    def subsets(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        bits = len(nums)
        nums.sort()
        result = []
        for i in range(2**bits):
            result.append(self.reflect(nums, i, bits))
        return result

    def reflect(self, nums, x, bits):
        mask = 0x01
        res = []
        for i in range(bits):
            if mask & x:
                res.append(nums[i])
            mask <<= 1
        return res
```

