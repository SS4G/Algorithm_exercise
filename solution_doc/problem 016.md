## 16. 3Sum Closest

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

#### tips
可以参考 ksum方法中 求3sum的方法 O(n^2)复杂度 
先排序
先确定一个数 nums[i] 然后从下标i之后去选取 j = i+1 k = len(nums) - 1 从两边往中间扫 扫的依据是
subTarget = target - nums[i]
if nums[j] + nums[k] > target
    k--;
elif nums[j] + nums[k] == target:
    break
else:
    j++
    
这个过程中记录与target差距最小的结果

这种扫的方法是ksum的通用方法
[ksum解法](http://blog.csdn.net/salutlu/article/details/25986239)

--------
#### update 
用java完成此题目的时候注意溢出问题
刚开始可能会把初始值设置为 Integer.MAX_VALUE 
但是如果此时target为-1 用初始值减去-1会导致溢出
同样 在使用绝对值函数的时候，如果直接对Integer.MIN_VALUE使用绝对值会导致 溢出

#### mycode_java
```
class Leet016 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int cloest = target > 0? Integer.MAX_VALUE - 3: Integer.MIN_VALUE + 3;
        for (int i = 0; i < nums.length - 2; i ++) {
            int leftTarget = target - nums[i];
            int leftIdx = i + 1;
            int rightIdx = nums.length - 1;
            int tmpSum = 0;
            while (leftIdx < rightIdx) {
                tmpSum = nums[leftIdx] + nums[rightIdx];
                if (tmpSum < leftTarget) {
                    leftIdx ++;
                }
                else if (tmpSum > leftTarget) {
                    rightIdx --;
                }
                else {
                    System.out.println("equ");
                    return target;
                }

                cloest = Math.abs(cloest - target) > Math.abs(tmpSum - leftTarget) ?nums[i] + tmpSum: cloest;
            }
        }
        return cloest;
    }
}
```


#### mycode_py

```
class Solution(object):
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        nums.sort()
        res = 0xffffffff
        for i in range(len(nums) - 2):
            il = i + 1
            ih = len(nums) - 1
            subTarget = target - nums[i]
            while il < ih:
                if il != i and ih != i:
                    s = nums[il] + nums[ih] + nums[i]
                    if abs(s - target) < abs(res - target):
                        res = s
                if nums[il] + nums[ih] < subTarget:
                    il += 1
                elif nums[il] + nums[ih] == subTarget:
                    return target
                else:
                    ih -= 1
        return res
```

    