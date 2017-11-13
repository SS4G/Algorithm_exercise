## 496. Next Greater Element I
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.


```
Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
```

Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
#### tips
在一开始的时候 建立一个关于nums2 value-index的字典
这样可以实现快速的查找value在nums2 的位置
#### mycode
```Python
class Solution(object):
    def nextGreaterElement(self, findNums, nums):
        """
        :type findNums: List[int]
        :type nums: List[int]
        :rtype: List[int]
        """
        length2 = len(nums)

        v_i_dict = {}
        for i in range(length2):
            v_i_dict[nums[i]] = i  # value:index
        res = []
        for j in findNums:
            index_in_nums = v_i_dict[j]  # get index of value to find
            if index_in_nums >= length2-1:  # last index
                res.append(-1)
                continue
            found_flag = False
            for index in range(index_in_nums+1, length2):
                if nums[index] > j:
                    res.append(nums[index])
                    found_flag = True
                    break
            if not found_flag:
                res.append(-1)
        return res
```
