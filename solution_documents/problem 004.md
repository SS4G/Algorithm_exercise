## 4. Median of Two Sorted Arrays
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
#### tips
[leetcode 004 题解](http://blog.csdn.net/zxzxy1988/article/details/8587244)  
主要是要注意边界情况 尤其是一次性可能将所有的应该在的元素都提出了的 情况 比如
arr0 = [1, 1]
arr1 = [1, 1]
#### mycode
```
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        if ((totalLen & 0x01) == 0) {
            //System.out.println(findKth(nums1, 0, nums2, 0, (totalLen >> 1) + 1));
            //System.out.println(findKth(nums1, 0, nums2, 0, (totalLen >> 1)));
            double tmp = (findKth(nums1, 0, nums2, 0, (totalLen >> 1) + 1) + findKth(nums1, 0, nums2, 0, totalLen >> 1));
            return tmp / 2;
        }
        else {
            return findKth(nums1, 0, nums2, 0, (totalLen >> 1) + 1);
        }
    }

    public int findKth(int[] num1, int start1, int[] num2, int start2, int k) {
        //System.out.println("num1 start:" + start1 + " num2 start:" + start2 + " find k:" + k);
        //edge case
        if (num1.length - start1 <= 0)

        if (num1.length - start1 <= 0) {
            return num2[start2 + k - 1];
        }

        if (num2.length - start2 <= 0) {
            return num1[start1 + k - 1];
        }

        if (k == 1) {
            return Math.min(num1[start1], num2[start2]);
        }

        //normal case;
        int bound;
        bound = (k >> 1) - 1;

        int bound1 = Math.min(start1 + bound, num1.length - 1);
        int bound2 = Math.min(start2 + bound, num2.length - 1);
        //System.out.println(bound + ":" + bound1 + ":" + bound2);
        if (num1[bound1] < num2[bound2]) {
            return findKth(num1, bound1 + 1, num2, start2, k - (bound1 + 1 - start1));
        }
        else if (num1[bound1] > num2[bound2]) {
            return findKth(num1, start1, num2, bound2 + 1, k - (bound2 + 1 - start2));
        }
        else {
            int restK = k - (bound2 + 1 - start2) - (bound1 + 1 - start1);
            if (restK == 0) {
                return num1[bound1];
            }
            else {
                return findKth(num1, bound1 + 1, num2, bound2 + 1, restK);
            }
        }

    }

}
```
