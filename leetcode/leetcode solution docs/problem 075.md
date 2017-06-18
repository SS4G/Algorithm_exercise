## 75. Sort Colors 
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.
#### tips
使用桶排序即可 很典型的桶排序应用 实现O(n)的复杂度
#### mycode
```Python
# beat 75%
class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        cnt = [0, 0, 0]
        for num in nums:
            cnt[num] += 1
        absIndex = 0
        for i in range(3):
            while cnt[i] > 0:
                nums[absIndex] = i
                cnt[i] -= 1
                absIndex += 1
```
