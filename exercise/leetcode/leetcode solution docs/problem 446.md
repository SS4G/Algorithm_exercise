## 442. Find All Duplicates in an Array Add to List

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?


```
Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
```

#### tips
用数组对应位置的正负来表示一个元素的出现次数 很妙
#### my_code
```Java
public class Solution {
     public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new LinkedList();
        for(int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] < 0) {
                res.add(Math.abs(nums[i]));
            }
            else {
                nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
            }
        }
        return res;
    }
}
```
