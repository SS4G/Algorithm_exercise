## 643. Maximum Average Subarray I
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].


#### tips
用一个滑动窗口即可

#### mycode
```
class Solution:
    def findMaxAverage(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: float
        """
        curWindowSum = 0
        maxWindowSum = -0xffffffff
        for i in range(k - 1, len(nums)):
            if i == k - 1:
                curWindowSum = sum(nums[:k])
            else:
                curWindowSum += (nums[i] - nums[i - k])
            maxWindowSum = max(maxWindowSum, curWindowSum)

        return maxWindowSum / k
```
