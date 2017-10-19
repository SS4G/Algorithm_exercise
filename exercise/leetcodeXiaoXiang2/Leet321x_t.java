package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.ArrayUtil;

import java.util.*;

class Leet321x {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] candit = new int[k];
        Arrays.fill(candit, 0);
        int totalLen = nums1.length + nums2.length;
        int totalAbort = totalLen - k;
        int abort1 = 0;
        int abort2 = totalLen;
        for (int i = 0; i < totalAbort + 1; i++) {
            abort1 = i;
            abort2 = totalAbort - i;
            if (nums1.length - abort1 < 0 || nums2.length - abort2 < 0)
                continue;
            int[] n1 = getMaxaxNumber(nums1, nums1.length - abort1);
            int[] n2 = getMaxaxNumber(nums2, nums2.length - abort2);
            int[] tmp = merge(n1, n2);
            candit = compareArr(candit, tmp) > 0 ? candit : tmp;
        }
        return candit;
    }

    public boolean greater(int[] num1, int nst1, int[] num2, int nst2) {
        while (nst1 < num1.length && nst2 < num2.length && num1[nst1] == num2[nst2]) {
            nst1++;
            nst2++;
        }
        return nst2 == num2.length || (nst1 < num1.length && num1[nst1] > num2[nst2]);
    }

    public int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int aPtr = 0;
        int bPtr = 0;
        int wPtr = 0;
        while (aPtr < a.length && bPtr < b.length) {
            if (a[aPtr] < b[bPtr]) {
                result[wPtr++] = b[bPtr++];
            }
            else if (a[aPtr] > b[bPtr]) {
                result[wPtr++] = a[aPtr++];
            }
            else {
                result[wPtr++] = greater(a, aPtr, b, bPtr) ? a[aPtr++] : b[bPtr++];
            }
        }

        while (aPtr < a.length)
            result[wPtr++] = a[aPtr++];

        while (bPtr < b.length)
            result[wPtr++] = b[bPtr++];

        return result;
    }

    public int[] getMaxaxNumber(int[] nums, int k) {
        if (k == 0)
            return new int[0];
        int[] stack = new int[k];
        int i = 0;
        int stackPtr = 0;
        while (i < nums.length) {
            if (stackPtr == 0)
                stack[stackPtr++] = nums[i];//push
            else if (stack[stackPtr - 1] < nums[i]) {
                while (stackPtr > 0 && stack[stackPtr - 1] < nums[i] && (k - stackPtr) < nums.length - i) {
                    stackPtr--;//pop()
                }
                stack[stackPtr++] = nums[i];
            }
            else {
                if (stackPtr < k)
                    stack[stackPtr++] = nums[i];
            }
            i++;
        }
        return stack;
    }

    public int compareArr(int[] a, int[] b) {
        assert a.length == b.length: "WTF";
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i])
                return -1;
            else if (a[i] > b[i])
                return 1;
        }
        return 0; //
    }
}

public class Leet321x_t {
    public static void main(String[] args) {
        Leet321x leet = new Leet321x();
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        //int[] result = leet.maxNumber(nums1, nums2, 5);
        //ArrayUtil.showArr(leet.getMaxaxNumber(nums1, 2));
        //ArrayUtil.showArr(leet.getMaxaxNumber(nums2, 0));
        //nums1 = new int[]{9, 8, 6, 3, 4, 7};
        //nums2 = new int[]{9, 8, 6, 3, 4, 9};

        //nums1 = new int[]{8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5};
        //nums2 = new int[]{7,8,4,1,9,4,2,6,5,2,1,2,8,9,3,9,9,5,4,4,2,9,2,0,5,9,4,2,1,7,2,5,1,2,0,0,5,3,1,1,7,2,3,3,2,8,2,0,1,4,5,1,0,0,7,7,9,6,3,8,0,1,5,8,3,2,3,6,4,2,6,3,6,7,6,6,9,5,4,3,2,7,6,3,1,8,7,5,7,8,1,6,0,7,3,0,4,4,4,9,6,3,1,0,3,7,3,6,1,0,0,2,5,7,2,9,6,6,2,6,8,1,9,7,8,8,9,5,1,1,4,2,0,1,3,6,7,8,7,0,5,6,0,1,7,9,6,4,8,6,7,0,2,3,2,7,6,0,5,0,9,0,3,3,8,5,0,9,3,8,0,1,3,1,8,1,8,1,1,7,5,7,4,1,0,0,0,8,9,5,7,8,9,2,8,3,0,3,4,9,8,1,7,2,3,8,3,5,3,1,4,7,7,5,4,9,2,6,2,6,4,0,0,2,8,3,3,0,9,1,6,8,3,1,7,0,7,1,5,8,3,2,5,1,1,0,3,1,4,6,3,6,2,8,6,7,2,9,5,9,1,6,0,5,4,8,6,6,9,4,0,5,8,7,0,8,9,7,3,9,0,1,0,6,2,7,3,3,2,3,3,6,3,0,8,0,0,5,2,1,0,7,5,0,3,2,6,0,5,4,9,6,7,1,0,4,0,9,6,8,3,1,2,5,0,1,0,6,8,6,6,8,8,2,4,5,0,0,8,0,5,6,2,2,5,6,3,7,7,8,4,8,4,8,9,1,6,8,9,9,0,4,0,5,5,4,9,6,7,7,9,0,5,0,9,2,5,2,9,8,9,7,6,8,6,9,2,9,1,6,0,2,7,4,4,5,3,4,5,5,5,0,8,1,3,8,3,0,8,5,7,6,8,7,8,9,7,0,8,4,0,7,0,9,5,8,2,0,8,7,0,3,1,8,1,7,1,6,9,7,9,7,2,6,3,0,5,3,6,0,5,9,3,9,1,1,0,0,8,1,4,3,0,4,3,7,7,7,4,6,4,0,0,5,7,3,2,8,5,1,4,5,8,5,6,7,5,7,3,3,9,6,8,1,5,1,1,1,0,3};
        //nums1 = new int[]{6, 0, 4};
        //ArrayUtil.showArr(leet.getMaxaxNumber(nums1, 3));
        //nums2 = new int[]{6, 0, 4};
        //nums1 = new int[]{6, 7};
        //nums2 = new int[]{6, 0, 4};
        //nums1 = new int[]{8, 6, 9};
        //nums2 = new int[]{1, 7, 5};
        int[] result = leet.maxNumber(nums1, nums2, 5);
        ArrayUtil.showArr(result);
    }
}
