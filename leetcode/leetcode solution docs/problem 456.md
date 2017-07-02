## 456. 132 Pattern
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:

```
Input: [1, 2, 3, 4]
```

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:

```
Input: [3, 1, 4, 2]
```

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:

```
Input: [-1, 3, 2, 0]
```

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

#### tips
先进性一次预处理 记录下到当前位置的最小元素是多少 然后用一个堆栈 记录当前可能的n[k] 从尾部向头部扫 当前扫到的元素对应的当前最小值 作为n[i] 当前扫到的元素作为 n[j] 

。。。 集体的描述见官方答案吧 太多了


#### tips
```
class Solution(object):
    def find132pattern(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) < 3:
            return False

        minI = 0xffffffff
        numsMin = [None, ] * len(nums)
        for i in range(len(nums)):
            minI = min(minI, nums[i])
            numsMin[i] = minI
        j = len(nums) - 1
        stack = []
        while j >= 1:
            if nums[j] > numsMin[j]:
                if len(stack) > 0 and nums[j] > stack[-1]:
                    return True
                stack.append(nums[j])
            else:
                while len(stack) > 0 and stack[-1] <= numsMin[j - 1]:
                    stack.pop()
            j -= 1
        return False
```
