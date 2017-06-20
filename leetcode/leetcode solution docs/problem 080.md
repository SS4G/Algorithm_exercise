## 80. Remove Duplicates from Sorted Array II

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

#### tips
使用桶排序的思想去先统计再写回
用一个字典来记录个数

#### mycode

```
class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        dictx = {}
        for i in nums:
            if i not in dictx:
                dictx[i] = 1
            else:
                dictx[i] += 1

        cnt = 0
        sortedKeys = list(dictx.keys())
        sortedKeys.sort()
        j = 0
        for i in sortedKeys:
            if dictx[i] >= 2:
                cnt += 2
                nums[j] = i
                nums[j+1] = i
                j += 2
            else:
                nums[j] = i
                j += 1
                cnt += 1
        return cnt
```
