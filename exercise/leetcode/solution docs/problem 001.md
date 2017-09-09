# leetcode 001
## Question
#### Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution.
Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.


## Answer
同样采用类似于冒泡排序的比较方法 
对于使用python的一处结果感到好奇，方法a是先通过循环找出能够达到target的值 然后在通过index（）来获取这两个值在原列表中的位置 方法b是每次循环时记录下index 找到达到target的值后直接保存当前的index
结果发现方法b反而比方法a慢2000ms 
说明每次保存index的操作并不高效 大部分时候保存的都是无用的index 

##### code

```Python
class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        nums_copy=nums
        a=0
        b=0
        same_flag=0

        k_index=0
        l_index=1
        for k in nums_copy:#a_index
            l_s_index=k_index+1
            l_index=l_s_index
            for l in nums_copy[l_s_index:]:#b_index 
                if l+k == target:
                    a=k_index
                    b=l_index
                    break
                l_index+=1
            k_index+=1
            
        index0=a
        index1=b
        res=[]    
        if index0>index1 :
            res=[index1,index0]
        else :
            res=[index0,index1]
        return res
```
