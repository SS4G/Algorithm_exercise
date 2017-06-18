##  34. Search for a Range

Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
#### tips
我的方法使用了三次二分查找 第一次查找 target
第二次查找 在第一次查找的基础上第一个target 第三次查找在第一次查找的基础上最后一个target
#### mycode

```
class Solution(object):
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        targetIndex = self.findTarget(nums, target)
        if targetIndex == -1:
            return [-1, -1]
        # print("tar index =", targetIndex)
        leftLim = self.findPart(nums, target, True, targetIndex)
        print("S")
        rightLim = self.findPart(nums, target, False, targetIndex)
        return [leftLim, rightLim]

    def findPart(self, nums, target, isLeftPart, targetIndex):
        if isLeftPart:  # find left start index
            dwnLim = 0
            upLim = targetIndex
            lo = (dwnLim + upLim) >> 1
            while dwnLim <= upLim:
                if nums[lo] == target and lo >= 1 and nums[lo-1] != target:
                    return lo
                elif nums[lo] == target and lo == 0:
                    return lo
                elif nums[lo] == target and lo >= 1 and nums[lo-1] == target:
                    upLim = lo - 1
                else:  # nums[lo] < target
                    dwnLim = lo + 1
                lo = (upLim + dwnLim) >> 1
            return lo
        else:
            dwnLim = targetIndex
            upLim = len(nums) - 1
            hi = (dwnLim + upLim) >> 1
            while dwnLim <= upLim:
                if nums[hi] == target and hi < len(nums) - 1 and nums[hi+1] != target:
                    return hi
                elif nums[hi] == target and hi == len(nums) - 1:
                    return hi
                elif nums[hi] == target and hi < len(nums) - 1 and nums[hi+1] == target:
                    dwnLim = hi + 1
                else:
                    upLim = hi - 1
                hi = (upLim + dwnLim) >> 1
            return hi

    def findTarget(self, nums, target):
        lo = 0
        hi = len(nums) - 1
        mid = (lo + hi) >> 1
        while lo <= hi:
            if nums[mid] > target:
                hi = mid - 1
            elif nums[mid] < target:
                lo = mid + 1
            else:
                return mid
            mid = (hi + lo) >> 1
        return -1
```

#### others code
采用了实现查找第一个大于等于目标元素的方法   firstGreaterEqual
然后用这个方法 分别取二分查找 target 以及 target+1  很简单
```
public class Solution {
	public int[] searchRange(int[] A, int target) {
		int start = Solution.firstGreaterEqual(A, target);
		if (start == A.length || A[start] != target) {
			return new int[]{-1, -1};
		}
		return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
	}

	//find the first number that is greater than or equal to target.
	//could return A.length if target is greater than A[A.length-1].
	//actually this is the same as lower_bound in C++ STL.
	private static int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			//low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				//should not be mid-1 when A[mid]==target.
				//could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}
}
```
