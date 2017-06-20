## 55. Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:

```
A = [2,3,1,1,4], return true.
```


```
A = [3,2,1,0,4], return false.
```

#### tips
这个题目当然可以从前往后 用回溯法 但是这样如果一个数组很长 
比如这种例子 [1,1,1,1,1,1,1,1,1....] 肯定会爆栈

所以应当换一个思路
就是从后往前 用一个指针从后向前扫， 用一个变量保存 当前可以调到尾部的 最小的索引

```
A = [2,3,1,1,4], return true.
I = [0,1,2,3,4]  #从后向前 都是当前能够达到最后一步的最小索引
```
只要一个新的元素可以到达当前的这个最小索引的位置， 那么这个新的元素就可以到达尾部， 然后用这个新的索引替代当前的最小索引因为他是最小的

#### mycode
```
class Solution(object):
    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        i = len(nums) - 1
        minIndex = i
        while i >= 0:
            if i + nums[i] >= minIndex:
                minIndex = i
            i -= 1
        return minIndex == 0
```
