## 268. Missing Number
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
#### tips
因为只要找出一个没有的数即可 那么可以看成是一个大数组 除了一个元素以外其他的元素两两成对 所以用异或运算来得到这个落单的元素 因为相同的数异或结果总是0 任何数和0 异或还是原数

##### mycode(beats 47%)

```Python
class Solution(object):
    def missingNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        xor=0
        length = len(nums)+1
        for i in range(length-1):
            xor ^= (nums[i] ^ i)
        xor ^= (length-1)
        return xor
```
