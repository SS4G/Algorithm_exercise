## 665. Non-decreasing Array

Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:

```
Input: [4,2,3]
Output: True
```

Explanation: You could modify the first 
4
 to 
1
 to get a non-decreasing array.
Example 2:

```
Input: [4,2,1]
Output: False
```

Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].

#### tips
存在两种情况 把中间高出来的那个部分压下去 也可以吧下面凹下去的提上来 这样就可以了 至于是哪一种情况 要去分析这个特殊点两边的情况 比较简单就不说累了

#### mycode

```
class Solution:
    def checkPossibility(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        cnt = 0
        idx = -1
        for i in range(len(nums)):
            if i < len(nums) - 1 and nums[i] > nums[i + 1]:
                idx = i
                cnt += 1
        if cnt > 1:
            return False
        elif cnt == 0:
            return True
        else:
            return  idx == 0 or idx + 1 == len(nums) - 1 or nums[idx - 1] <= nums[idx + 1] or nums[idx] <= nums[idx + 2]
```





