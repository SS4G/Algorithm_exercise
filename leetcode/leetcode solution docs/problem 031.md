## 31. Next Permutation 

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers. 
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order). 
The replacement must be in-place, do not allocate extra memory. 
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

```
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
```

#### tips
注意观察 按照字典序下一个序列应该是什么的规律
需要改变的地方出现在 nums[i] < nums[i+1] 的地方 。。
如果全部的nums[i] >= nums[i+1]那么直接按照升序排序即可回到最小字典序的组合

#### mycode

```
class Solution(object):
    def nextPermutation(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        if len(nums) <= 1:
            return
        i = len(nums) - 1
        allLessFlag = True
        while i >= 1:
            if nums[i] <= nums[i - 1]:
                i -= 1
            else:
                allLessFlag = False
                break
        if allLessFlag:
            nums.sort()
        else:
            part = nums[i - 1:]
            oldFirstNum = part[0]
            part.sort()
            newFirstNumIndex = 0
            for j in range(len(part)):
                if part[j] > oldFirstNum:
                    newFirstNumIndex = j
                    break
            tmp = part[newFirstNumIndex]
            part[newFirstNumIndex] = part[0]
            part[0] = tmp
            resPart = part[1:]
            resPart.sort()
            part[1:] = resPart[:]
            nums[i - 1:] = part[:]
            return

if __name__ == "__main__":
    s = Solution()
    print(s.nextPermutation([1, 2, 3, 3, 2]))
```





