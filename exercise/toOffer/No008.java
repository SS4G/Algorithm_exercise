package AlgorithmTraining.exercise.toOffer;
import java.util.ArrayList;

class Solution08 {
    public int minNumberInRotateArray(int [] array) {
        int length = array.length;
        if (length == 0)
            return 0;
        else if (length == 1) {
            return array[0];
        }
        else if (array[0] < array[length - 1]) {
            return array[0];
        }
        else if (array[0] == array[length - 1]) {
            int minVal = Integer.MAX_VALUE;
            for (int i : array) {
                if (minVal > i) {
                    minVal = i; 
                }
            }
            return minVal;
        }
        else {
            int lo= 0;
            int hi = length - 1;
            int avrVal = (array[0] + array[length - 1]) / 2;
            int mid;
            while (lo <= hi) {
                mid = (lo + hi) >> 1;
                if (array[mid] > avrVal) {
                    lo = mid + 1;
                } 
                else {
                    if (array[mid - 1] <= array[mid]) {
                        hi = mid - 1;
                    }
                    else {
                        return array[mid];
                    }
                }
            } 
            return 0;
        }
    }
}

public class No008 {
    public static void main(String[] args) {
        int[] arr0 = {1, 2, 3, 4, 5, 6};
        int[] arr1 = {5, 6, 1, 2, 3, 4};
        int[] arr2 = {2, 2, 2, 1, 2, 2};
        int[] arr3 = {};
        int[] arr4 = {1};
        int[] arr5 = {6501,6828,6963,7036,7422,7674,8146,8468,8704,8717,9170,9359,9719,9895,9896,9913,9962,154,293,334,492,1323,1479,1539,1727,1870,1943,2383,2392,2996,3282,3812,3903,4465,4605,4665,4772,4828,5142,5437,5448,5668,5706,5725,6300,6335};
        int[] arr6 = {5, 5, 6, 6, 1, 1, 1, 2, 2, 3, 4, 4, 4};
        Solution08 s = new Solution08();
        assert s.minNumberInRotateArray(arr0) == 1;
        assert s.minNumberInRotateArray(arr1) == 1;
        assert s.minNumberInRotateArray(arr2) == 1;
        assert s.minNumberInRotateArray(arr3) == 0;
        assert s.minNumberInRotateArray(arr4) == 1;
        assert s.minNumberInRotateArray(arr5) == 154;
        assert s.minNumberInRotateArray(arr6) == 1;
    }
}
