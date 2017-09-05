# leetcode 189
## Question
#### Rotate Array
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
## Answer
使用python 內建的分片赋值来处理

```Python
class Solution(object):
    def rotate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        n=len(nums)
        i=-1
        tmp=None
        front=nums[:n-k]#保存前部
        del(nums[:n-k])#删除前部
        nums.extend(front)#将前部移到后部
        #print(nums)
```
