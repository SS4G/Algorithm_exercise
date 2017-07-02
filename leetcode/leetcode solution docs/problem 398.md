## 398. Random Pick Index

Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);


#### tips
根据题目的要求用的还是水库抽样算法 只不过加了一个限制条件 就是抽取某些值的对象

#### mycode

```
import random
class Solution(object):
    def __init__(self, nums):
        """
        :type nums: List[int]
        :type numsSize: int
        """
        self.nums = nums

    def pick(self, target):
        """
        :type target: int
        :rtype: int
        """
        cnt = 0
        flag = False
        r = -1
        for i in range(len(self.nums)):
            if self.nums[i] == target:
                if flag is False:
                    flag = True
                    r = i
                cnt += 1
                r = i if random.randint(1, cnt) == 1 else r
        return r
```
