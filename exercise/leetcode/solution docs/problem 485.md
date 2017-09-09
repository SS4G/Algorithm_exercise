### 485. Max Consecutive Ones   Add to List 
Given a binary array, find the maximum number of consecutive 1s in this array.


```
Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:
```


The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
##### mycode
```
class Solution(object):
    def findMaxConsecutiveOnes(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        maxs=[]
        last=0
        current=0
        for i in nums:
            current+=i
            if current>last:
                last=current
                continue
            else:
                maxs.append(current)
                last=0
                current=0
        maxs.append(current) #可能有重复 但因为是求最大值无所谓 如果没有这一句 那么当最后都是1的情况将被漏掉！！
        return max(maxs)
```
