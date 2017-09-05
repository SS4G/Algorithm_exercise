## 300. Longest Increasing Subsequence
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

- Follow up: Could you improve it to O(n log n) time complexity?

#### tips
这个题目是典型的动态规划题目， 子问题是 以当前点结尾的最长增长序列是多长 根据新的节点 S(i) = 1 + max(S[r])
r 为i前面所有小于等于num[i]的点

#### mycode

```
class Solution(object):
    def lengthOfLIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) <= 1:
            return len(nums)

        records = [0, ] * len(nums)
        for i in range(len(nums)):
            records[i] = 1
            for j in range(0, i + 1):
                if nums[i] > nums[j]:
                    records[i] = max(records[j] + 1, records[i])
        return max(records)
```
