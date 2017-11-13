## 674. Longest Continuous Increasing Subsequence

Given an unsorted array of integers, find the length of longest continuous increasing subsequence.


```
Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
Note: Length of the array will not exceed 10,000.
```

#### tips
写一个简单的状态机 注意特殊情况 使用while能够更好的控制下标

#### mycode

```
class Solution:
    def findLengthOfLCIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        state = False
        last = 0
        idx = 0
        maxLen = 0
        cnt = 0
        while idx < len(nums):
            #print(idx)
            if state is False:
                state = True
                last = nums[idx]
                cnt = 1
                idx += 1
            else:
                if nums[idx] > last:
                    last = nums[idx]
                    cnt += 1
                    idx += 1
                else:
                    state = False
                    maxLen = max(cnt, maxLen)
        maxLen = max(cnt, maxLen)
        return maxLen
```
