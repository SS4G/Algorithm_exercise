## 238. Product of Array Except Self
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)


#### tips

首先使用累计cheng乘法的方式先算出下面-线两侧的数列存储起来这个时间复杂度为O(n) 然后将-线两边的部分乘起来 从 0 ～ n-1 这个过程也是O(n)两个过程是串行的所以整体的时间复杂度度也是O(n) 空间复杂度O(N)
```
求 [a,b,c,d,e]
-bcde
a-cde
ab-de
abc-e
abcd-
```

#### mycode
```Python
class Solution(object):
    def productExceptSelf(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        left_res = []
        right_res = []

        n =len(nums)
        if n == 2:
            return [nums[1], nums[0]]

        part_product = 1

        i = 0
        while i < n:
            left_res.append(part_product)
            part_product *= nums[i]
            i += 1

        i = n-1
        part_product = 1
        while i >= 0:
            right_res.append(part_product)
            part_product *= nums[i]
            i -= 1
        right_res.reverse()
        return [left_res[i]*right_res[i] for i in range(n)]
```

#### others code
time O(n) Space O(1) : ps:result is exclude 
```Java
public class Solution {
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        res[i] *= right;
        right *= nums[i];
    }
    return res;
}
```
