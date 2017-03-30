# leetcdoe 303
## Question
#### Range Sum Query - Immutable
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:

```
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
```

Note:
- You may assume that the array does not change.
- There are many calls to sumRange function.

## Answer
此题目给的数组是不变的意在 后面计算的结果可以依赖之前的部分结果
后面要求的区间与前面重叠的话可以不用重复计算

##### code


```
class NumArray(object):
    def __init__(self, nums):
        """
        initialize your data structure here.
        :type nums: List[int]
        """
        self.last_i=0
        self.last_j=0
        self.last_sum=0
        self.nums2=nums
        self.firstcall=1
    def sumRange(self, i, j):
        """
        sum of elements nums[i..j], inclusive.
        :type i: int
        :type j: int
        :rtype: int
        """ 
        #当然可以进一步优化 如果 ij 距离比较短而且离 last_i,lats_j 比较远的话可以直接计算 sum(nums2[i:j+1])
        if not firstcall:        
            if   (self.last_i<=i<=self.last_j) and (self.last_i<=j<=self.last_j):                
                res=self.last_sum-sum(self.nums2[self.last_i:i])-sum(self.nums2[j+1:self.last_j+1]) 
            elif (self.last_i<=i<=self.last_j) :
                res=self.last_sum-sum(self.nums2[self.last_i:i])+sum(self.nums2[self.last_j+1:j+1])
            elif (self.last_i<=j<=self.last_j) :
                res=self.last_sum-sum(self.nums2[j+1:self.last_j+1])+sum(self.nums2[i:self.last_i])
            else:
                res=sum(self.nums2[i:j+1])
        else:
            res=sum(self.nums2[i:j+1]) 
            firstcall=0
             
             
        self.last_i=i
        self.last_j=j
        self.last_sum=res
        return res
# Your NumArray object will be instantiated and called as such:
# numArray = NumArray(nums)
# numArray.sumRange(0, 1)
# numArray.sumRange(1, 2)
```

