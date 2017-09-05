## 162. Find Peak Element 

Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
#### tips
好像没啥好说的 找个极大值

#### mycode
顺序查找
```
class Solution(object):
    def findPeakElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return 0

        for i in range(len(nums)):
            if i == 0:
                if nums[i] > nums[i + 1]:
                    return i
            elif i == len(nums) - 1:
                if nums[i] > nums[i - 1]:
                    return i
            else:
                if nums[i - 1] < nums[i] > nums[i + 1]:
                    return i
```

#### otheer
二分查找
```
class Solution {
public:
    int findPeakElement(vector<int>& nums) {
        int low = 0;
        int high = nums.size()-1;
        while(low < high)
        {
            int mid = low + (high-low)/2;
            if(nums[mid] > nums[mid+1])
                high = mid;
            else
                low = mid+1;
        }
        return low;
    }
};
```
