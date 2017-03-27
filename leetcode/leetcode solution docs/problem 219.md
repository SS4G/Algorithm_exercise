# leetcode 219
## Question
#### Contains Duplicate II
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
## Answer
一般比较傻得方法 就是先排序 同时记住下标
然后在排好序的列表中去找相同的值 然后在比较他们的下标
结果是TLE 最坏的时间复杂度O(N^2)

另一种是设定一个长度为k的窗口 在窗口内的范围查找相同的值
复杂度O(N*K)
在k很大的时候 估计也会TLE

最后通过的一种是 使用Python的字典 
如果第一次遇到某个值 就以该值为字典项的索引将其下标加入到字典中
否则 从字典中查找对应的的值的下标 看是否在<=k的范围内
如果不在 就用新的下标来更新字典中原来的下标

主要快就快在 字典的查找操作 至于有多快尚不明确
##### code
```Python
class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        dicts={}
        length=len(nums)
        for i in range(length):
            if nums[i] not in dicts:
                dicts[nums[i]]=i
            else:
                if i-dicts[nums[i]]<=k:
                    return True
                else :
                    dicts[nums[i]]=i
        return False
```
