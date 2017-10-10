package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
class Leet004x {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        if ((totalLen & 0x01) == 0) {
            return ((double)findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, totalLen >> 1) +
                    (double)findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, (totalLen >> 1) + 1)) / 2;
        }
        else {
            return (double)findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, (totalLen >> 1) + 1);
        }
    }

    public int findKthNumInSortedArray(int[] nums1, int bg1, int ed1, int[] nums2, int bg2, int ed2, int k) {
        //System.out.println("nums1:" + bg1 + ":" + ed1 + " nums2:" + bg2 + ":" + ed2 + " k=" + k);
        assert k >= 0 && ((ed1 - bg1) + (ed2 - bg2) >= k);
        //one array is empty
        if (ed1 - bg1 <= 0)
            return nums2[bg2 + k - 1];
        if (ed2 - bg2 <= 0)
            return nums1[bg1 + k - 1];
        if (k == 1)
            return Math.min(nums1[bg1], nums2[bg2]);

        int kth2div;
        if ((k & 0x01) == 0) // k is even
            kth2div = k >> 1;
        else
            kth2div = (k - 1) >> 1;

        int checkPos1 = Math.min(bg1 + kth2div - 1, nums1.length - 1); //check point of nums1
        int checkPos2 = Math.min(bg2 + kth2div - 1, nums2.length - 1); //check point of nums2
        if (nums1[checkPos1] > nums2[checkPos2]) { //abort number include nums2[checkPos2]
            return findKthNumInSortedArray(nums1, bg1, ed1, nums2, checkPos2 + 1, ed2, k - (checkPos2 + 1 - bg2));
        }
        else if (nums1[checkPos1] < nums2[checkPos2]) { //abort number include nums1[checkPos1]
            return findKthNumInSortedArray(nums1, checkPos1 + 1, ed1, nums2, bg2, ed2, k - (checkPos1 + 1 - bg1));
        }
        else {
            if (k - (checkPos1 + 1 - bg1) - (checkPos2 + 1 - bg2) == 0)
                return nums1[checkPos1];
            return findKthNumInSortedArray(nums1, checkPos1 + 1, ed1, nums2, checkPos2 + 1, ed2, k - (checkPos1 + 1 - bg1) - (checkPos2 + 1 - bg2));
        }
    }
}

public class Leet004x_t {
    public static void main(String[] args) {
        Leet004x leet = new Leet004x();
        int[] nums1 = {1, 3, 5, 7, 9};
        int[] nums2 = {1, 3, 5, 7, 9};
        //assert leet.findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, 10) == 9;
        nums1 = new int[]{1, 2, 3, 4, 5, 6};
        nums2 = new int[]{3};
        //assert leet.findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, 4) == 3;
        nums1 = new int[]{1, 2, 3};
        nums2 = new int[]{};
        //assert leet.findMedianSortedArrays(nums1, nums2) - 2.0 < 0.001;
        //assert leet.findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, 2) == 2;
        nums1 = new int[]{1, 3, 5, 7, 9};
        nums2 = new int[]{0, 2, 4, 6, 8};
        //assert leet.findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, 5) == 4;
        //assert leet.findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, 6) == 5;
        //assert leet.findMedianSortedArrays(nums1, nums2) - 4.5 < 0.001;
        nums1 = new int[]{1, 2};
        nums2 = new int[]{1, 2};
        //assert leet.findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, 2) == 1;
        //assert leet.findKthNumInSortedArray(nums1, 0, nums1.length, nums2, 0, nums2.length, 3) == 2;
        assert leet.findMedianSortedArrays(nums1, nums2) - 1.5 < 0.001;
    }
}
