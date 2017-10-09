package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by BUPT_SS4G on 2017/9/18.
 */

class Leet004 {
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

public class Leet004_t {
    public static void main(String[] args) {
        Leet004 leet = new Leet004();
        int[] arr0 = {1, 3, 5, 7, 9};
        int[] arr1 = {0, 2, 4, 6, 8};
        //assert Math.abs(leet.findMedianSortedArrays(arr0, arr1) - 4.5) < 0.00001;
        arr0 = new int[]{1, 3, 5, 7, 9};
        arr1 = new int[]{0, 2, 4, 6};
        //assert Math.abs(leet.findMedianSortedArrays(arr0, arr1) - 4) < 0.00001;
        arr0 = new int[]{1};
        arr1 = new int[]{0};
        //assert Math.abs(leet.findMedianSortedArrays(arr0, arr1) - 0.5) < 0.00001;
        arr0 = new int[]{2, 3, 4, 5, 6};
        arr1 = new int[]{1};
        //assert Math.abs(leet.findMedianSortedArrays(arr0, arr1) - 3.5) < 0.00001;
        arr0 = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
        arr1 = new int[]{0,6};
        //assert Math.abs(leet.findMedianSortedArrays(arr0, arr1) - 10.5) < 0.00001;
        arr0 = new int[]{1,1};
        arr1 = new int[]{1,1};
        //assert Math.abs(leet.findMedianSortedArrays(arr0, arr1) - 1.0) < 0.00001;
        System.out.println(leet.findKth(arr0, 0, arr1, 0, 2));
        //System.out.println(leet.findKth(arr0, 0, arr1, 0, 4));
        //System.out.println(Math.abs(leet.findMedianSortedArrays(arr0, arr1) - 4.5));
    }
}
