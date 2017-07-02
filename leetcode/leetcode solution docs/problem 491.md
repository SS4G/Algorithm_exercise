## 491. Increasing Subsequences

Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:

```
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
```

Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

#### tips
按照起点 抽取出大于每个起点的 序列 然后对这个序列按照dfs的方式去生成 以这个点为开头的子序列

注意是以这个定点开头的

最终的结果要注意用set去重 [4,7] [4,7] 算是重复的结果

#### mycode


```
class Solution(object):
    def findSubsequences(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        allSubSequence = []
        for j in range(len(nums)):
            thisTurnArr = []
            for i in range(j, len(nums)):
                if nums[i] >= nums[j]:
                    thisTurnArr.append(nums[i])
            allSubSequence.append(thisTurnArr)
        finalRes = set([])
        for i in allSubSequence:
            finalRes = finalRes | self.genSubseq(i)
        return [list(i) for i in finalRes]

    def genSubseq(self, arr):
        stack = []
        output = set([])
        self.genSubSeqRecursive(arr, 0, stack, output)
        return output

    def genSubSeqRecursive(self, arr, stIndex, stack, output):
        if stIndex == len(arr):
            return
        else:
            stack.append(arr[stIndex])
            if stIndex != 0:
                output.add(tuple(stack[:]))
            for i in range(stIndex + 1, len(arr)):
                if arr[i] >= arr[stIndex]:
                    self.genSubSeqRecursive(arr, i, stack, output)
            stack.pop()
            return
```


