## 324. Wiggle Sort II
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
- (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
- (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

#### tips
想象着吧一个排列后的数列从中位数的位置切开 然后将较大的一部分从和较小的一部分相间的插入到新的位置 

交大的一部分递减 较小的一部分递减。 两者不可能有相邻且相等的值 因为如果在这个过程中出现这个问题 说明这个划分出来的序列至少有一半以上的值大于中位数 除非当初划分中位数有问题， 否则不可能出现这种情况

#### mycode

```
class Solution(object):
    def wiggleSort(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        size = len(nums)
        snums = sorted(nums)
        for x in list(range(1, size, 2)) + list(range(0, size, 2)):
            nums[x] = snums.pop()
        return
```
