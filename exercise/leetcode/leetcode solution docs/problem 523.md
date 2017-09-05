## 523. Continuous Subarray Sum

Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:

```
Input: [23, 2, 4, 6, 7],  k=6
Output: True
```

Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

```
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
```

Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.


#### tips
这类题目 使用的技术是 presum + hashmap
首先将各个位置到数组起始位置的和求出来 复杂度为O(n)
然后根据相对位置 如需要找 和为k的子序列 如果字典中有sum 那么就再去找 sum-k 如果也有 再看看sum-k中对应的下标有没有比sum对应下标小的 如果也成立 那么就找到了

这个要k的倍数 只要用字典跟踪余数即可 空间复杂度k 具体看代码就懂了 

#### mycdoe

```
class Solution(object):
    def checkSubarraySum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        if len(nums) < 2:
            return False
        sumRec = [-1, ] * len(nums)
        for i in range(len(nums)):
            if i == 0:
                sumRec[i] = nums[0]
            else:
                sumRec[i] = nums[i] + sumRec[i - 1]
        if k == 0:
            return True if sumRec[-1] == 0 else False

        markDict = {}
        for i in range(len(sumRec)):
            res = sumRec[i] % abs(k)
            if res == 0 and i != 0:
                return True
            elif res in markDict:
                return True
            else:
                markDict[res] = 1
        return False
```
