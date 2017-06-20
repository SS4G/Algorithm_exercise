## 46. Permutations
DescriptionHintsSubmissionsSolutions
Total Accepted: 163653
Total Submissions: 383485
Difficulty: Medium
Contributor: LeetCode
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:

```
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

#### mycode
一个简单的全排列 由于没有重复数字 不用考虑重复顺序相同的情况 用最简单的回溯法 结合一个标记数组来标记那些元素当前还是可用的 来完成这个任务 
#### tips
```
class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        output = []
        mark = [False, ]*len(nums)
        stack = []
        self.gen(nums, 0, mark, stack, output)
        return output

    def gen(self, arr, pos, mark, stack, output):
        useable = list(filter(lambda x: not mark[x], range(0, len(arr))))
        for i in useable:
            stack.append(arr[i])
            mark[i] = True
            if pos == len(arr) - 1:
                output.append(stack[:])
                stack.pop()
                mark[i] = False
            else:
                self.gen(arr, pos + 1, mark, stack, output)
                stack.pop()
                mark[i] = False
        return


if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3, 4]
    res = s.permute(arr)
    print(len(res))
    for j in res:
        print(j)
```
