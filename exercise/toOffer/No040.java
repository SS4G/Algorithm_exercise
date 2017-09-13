package AlgorithmTraining.exercise.toOffer;

/**
 * Created by szh-920 on 17-9-11.
 */
class Solution040 {
    public int GetNumberOfK(int [] array , int k) {
        int leftBound = findLeftBound(array, k);
        int rightBound = findRightBound(array, k);
        if (leftBound != -1 && rightBound != -1) {
            return rightBound - leftBound + 1;
        }
        else {
            return 0;
        }
    }

    private int findLeftBound(int[] arr, int k) {
        int hi = arr.length - 1;
        int lo = 0;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (arr[mid] == k && (mid == 0 || arr[mid - 1] < k)) {
                return mid;
            }
            else if (arr[mid] >= k) {
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    private int findRightBound(int[] arr, int k) {
        int hi = arr.length - 1;
        int lo = 0;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (arr[mid] == k && (mid == arr.length - 1 || arr[mid + 1] > k)) {
                return mid;
            }
            else if (arr[mid] <= k) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}

public class No040 {
    public static void main(String[] args) {
        Solution040 s = new Solution040();
        int[] arr = {1, 1, 2, 3, 4, 4, 4, 5, 5, 5, 5, 6, 7, 8, 8, 8, 9, 15, 15, 15};
        assert s.GetNumberOfK(arr, 5) == 4;
        assert s.GetNumberOfK(arr, 1) == 2;
        assert s.GetNumberOfK(arr, 3) == 1;
        assert s.GetNumberOfK(arr, 4) == 3;
        assert s.GetNumberOfK(arr, 8) == 3;
        assert s.GetNumberOfK(arr, 9) == 1;
        assert s.GetNumberOfK(arr, 15) == 3;
        assert s.GetNumberOfK(arr, 10) == 0;
        assert s.GetNumberOfK(arr, 16) == 0;
        assert s.GetNumberOfK(arr, 0) == 0;
    }
}
