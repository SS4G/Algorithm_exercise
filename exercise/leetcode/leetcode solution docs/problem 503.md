## 503. Next Greater Element II Add to List

Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.


```
Example 1:
Input: [1,2,1]
Output: [2,-1,2]
```

Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.

#### tips
使用堆栈来处理这个问题可以达到O(n)的性能 每当获取到一个元素a的时候就应该和栈中的元素比较 如果栈中的元素小就将它们弹出， 弹出的过程中， 将这些元素的nextgreater标记为当前获取到的元素a， 直到栈内所有的元素都大于等于当前获取到的元素a， 将a压到栈中。  
注意获取元素的起始的位置应该是从整个序列的最大值处开始

#### mycode
```Python
class Solution(object):
    def nextGreaterElements(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        if not nums:
            return []
        indexStack = []
        res = [-1, ]*len(nums)
        nextI = lambda x: x+1 if x < len(nums)-1 else 0
        max = -0xffffffff
        maxI = -1
        for i in range(len(nums)):
            maxI = i if nums[i] > max else maxI
            max = nums[i] if nums[i] > max else max
        i = nextI(maxI)
        indexStack.append(maxI)
        cyc = True
        while i != nextI(maxI) or cyc:
            cyc = False
            while nums[indexStack[-1]] < nums[i]:
                popedIndex = indexStack.pop()
                res[popedIndex] = nums[i]
            indexStack.append(i)
            i = nextI(i)
        return res
```
