# leetcode 217
## Question
#### Contains Duplicate

Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

## Answer
方案1 
使用python list内置的count()方法
复杂度O(n^2)
在测试用例非常长的情况下会挂掉

方案2
先排序 在遍历一次即可 通过比较上次遍历列表的结果和当前结果比较 如果相同则 返回True 否则遍历整个列表后返回false
复杂度遍历的话是O(n) 主要看Python的排序
这种记录上次遍历结果的方法相当常用 在FPGA中也经常用于采样信号变化边沿


##### code
方案1

```Python
class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        for n in nums :
            if nums.count(n)>=2:#O(n^2) toooo SB!
                return True
            
        return False
```

        
方案2

```Python
class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        nums.sort()
        n_last=None
        for n in nums :
            if n_last==n:
                return True
            n_last=n
            
        return False
```


