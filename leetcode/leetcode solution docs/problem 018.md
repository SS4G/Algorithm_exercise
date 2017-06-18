## 18. 4Sum

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:

```
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```
#### tips
先两两计算出任意两个数字的和， 然后用字典存储 这个和 因为每个和可能存在多种情况 所以用字典保存所有这些情况可能的 加数的位置

如 对于[0,1,2,3,4,5]和为 5
dict{5:[(1, 4), (2,3)}

然后选取一个数的和 v在字典中寻找是否有 target -v 如果存在 还要判断他们的加数是否有重复 如果没有就把对应的数对作为结果保存到output 为了防止把v target-v计算两次 需要 保证 v>=target-v
最后用set把最终的结果去重

#### mycode

```
class Solution(object):
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        dictPartRes = {}
        for i in range(len(nums)):
            for j in range(i+1, len(nums)):
                if nums[i] + nums[j] not in dictPartRes:
                    dictPartRes[nums[i]+nums[j]] = [(i, j)]
                else:
                    dictPartRes[nums[i]+nums[j]].append((i, j))
        res = []
        for v in dictPartRes:
            if target-v in dictPartRes and v >= target-v:
                for a in dictPartRes[v]:
                    for b in dictPartRes[target-v]:
                        if len(set([a[0], a[1], b[0], b[1]])) == 4:  # assure four different number
                            res.append([nums[a[0]], nums[a[1]], nums[b[0]], nums[b[1]]])
        for j in res:
            j.sort()
        res = set([(i[0], i[1], i[2], i[3])for i in res])
        finRes = []
        for k in res:
            finRes.append(list(k))
        return finRes

if __name__ == "__main__":
    s = Solution()
    arr = [1, 0, -1, 0, -2, 2, -1, -1, 1, 1, 1, -1]
    print(s.fourSum(arr, 0))
```
