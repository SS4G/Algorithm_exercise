## 414. Third Maximum Number   
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).


```
Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1
```

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
##### mycode(beats 77%)
一个巧妙的方法  呵呵呵 使用集合是为了去除列表中重复的元素
```
class Solution(object):
    def thirdMax(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums=list(set(nums))
        if len(nums)<3:
            return max(nums)
        maxs=[-12147483648,-12147483648,-12147483648]
        for i in nums:
            if i==maxs[0] or i==maxs[1] or i==maxs[2]:
                continue
            if i >maxs[0]:
                maxs[2]=maxs[1]
                maxs[1]=maxs[0]
                maxs[0]=i
            elif i>maxs[1]:
                maxs[2] = maxs[1]
                maxs[1] = i
            elif i>maxs[2]:
                maxs[2] =i
        return maxs[2]
```
