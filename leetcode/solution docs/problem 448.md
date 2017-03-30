## 448. Find All Numbers Disappeared in an Array  
Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 14188
Total Submissions: 24762
Difficulty: Easy
Contributors: yuhaowang001
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

```
Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
```

Subscribe to see which companies asked this question

##### mycode 
更简单的方法 是使用集合  使用差集计算 但是集合的复杂度尚不知道



```Python
class Solution(object):
    def findDisappearedNumbers(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        #std=list(range(1,length+1))
        res=[]
        nums.sort()
        length=len(nums)
        cur=1
        std=list(range(length+1))
        print(std)
        for i in range(length):
            if nums[i]==cur:
                cur+=1
            elif nums[i]<cur:
                pass
            elif nums[i]>cur:
                res+=std[cur:nums[i]]#list(range(cur,nums[i]))
                cur=nums[i]+1
        res+=std[cur:length+1]#list(range(cur,length+1))
        return res
```
