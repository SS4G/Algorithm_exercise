##  34. Search for a Range

Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

#### tips
update 1017-10-11

使用二分查找 查找左边界和右边界 查找右边界的函数在左边界上的函数基础上稍微改一下就可以 很快

#### mycode
```
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = searchLeft(nums, target);
        res[1] = searchRight(nums, target);
        return res;
    }

    int searchLeft(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            }
            else if (nums[mid] > target) {
                hi = mid - 1;
            }
            else {
                if (mid == 0)
                    return 0;
                else if (nums[mid - 1] == target) {
                    hi = mid - 1;
                }
                else {
                    return mid;
                }
            }
        }
        return -1;
    }

    int searchRight(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            }
            else if (nums[mid] > target) {
                hi = mid - 1;
            }
            else {
                if (mid == nums.length - 1)
                    return nums.length - 1;
                else if (nums[mid + 1] == target) {
                    lo = mid + 1;
                }
                else {
                    return mid;
                }
            }
        }
        return -1;
    }
}
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




