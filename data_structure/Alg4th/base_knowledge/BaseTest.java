package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

/**
 * Created by BUPT_SS4G on 2017/6/6.
 */
class SuperPow{
    //calculate the x^n
    public static int superPow(int x, int n) throws ValueException{
        if (n < 0 || (x == 0 && n == 0))
            throw new ValueException("Invalid arguments");
        if (n == 0)
            return 1;
        if (x == 1 || x == 0)
            return x;
        if ((n & 0x01) != 0)
            return superPow(x*x, n >> 1)*x;
        else
            return superPow(x*x, n >> 1);
    }
}

class Gcd {
    //使用辗转相除法
    public static int gcd0(int x, int y) throws ValueException{
        if (x <= 0 || y <= 0)
            throw new ValueException("invalid value of arguments");
        int tmp;
        if (x < y) {
          tmp = x;
          x = y;
          y = tmp;
        }
        while (x % y !=0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return y;
    }
    //使用更像减损术
    public static int gcd1(int x, int y) throws ValueException{
        if (x <= 0 || y <= 0)
            throw new ValueException("invalid value of arguments");
        int tmp;
        if (x < y) {
            tmp = x;
            x = y;
            y = tmp;
        }
        while (x - y > 0) {
            int r = x - y;
            x = y > r ? y : r;
            y = y <= r ? y : r;
        }
        return y;
    }
}

class BinarySearch {
    // demo function
    // return the index where the target is  return -1 if target is not found
    public static int binarySearch(int[] arr, int target) {
        // assume the array is already sorted
        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            if (arr[mid] == target) {
                return mid;
            }
            else if (arr[mid] < target) {
                lo = mid + 1;
            }
            else
                hi = mid - 1;
        }
        return -1;
    }
}

class MaxSubArray {
    //O(n^3)
    public static int maxSubArray0(int arr[]) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++)
                    sum += arr[k];

                if (maxSum < sum)
                    maxSum = sum;
            }
        return maxSum;
    }

    //O(n^2)
    public static int maxSubArray1(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    //O(nlgn)
    public static int maxSubArray2(int[] arr) {
        return maxSubArray2Recursion(arr, 0, arr.length - 1);
    }

    private static int maxSubArray2Recursion(int[] arr, int lo, int hi) {
        if (hi - lo == 1) {
            int max = Math.max(arr[lo] + arr[hi], arr[lo]);
            max = Math.max(max, arr[hi]);
            return max;
        }
        else if (hi - lo == 0)
            return arr[lo];
        else {
            int mid = (lo + hi) >> 1;
            int max0 = Integer.MIN_VALUE;
            int partMax0 = Integer.MIN_VALUE;
            for (int i = mid - 1, sum = 0; i >= lo; i--) {
                sum += arr[i];
                if (partMax0 < sum)
                    partMax0 = sum;
            }
            int partMax1 = Integer.MIN_VALUE;
            for (int i = mid + 1, sum = 0; i <= hi; i++) {
                sum += arr[i];
                if (partMax1 < sum)
                    partMax1 = sum;
            }
            max0 = partMax0 + partMax1 + arr[mid];
            int max1 = maxSubArray2Recursion(arr, lo, mid - 1);
            int max2 = maxSubArray2Recursion(arr, mid + 1, hi);
            return Math.max(max0, Math.max(max1, max2));
        }
    }

    //O(n)
    public static int maxSubArray3(int[] arr) {
        if (arr.length == 1)
            return arr[0];
        else {
            int maxSum = Integer.MIN_VALUE;
            int partSum = 0;
            for (int i = 0; i < arr.length; i++) {
                partSum += arr[i];
                maxSum = partSum > maxSum ? partSum : maxSum ;
                partSum = partSum < 0 ? 0 : partSum;
            }
            return maxSum;
        }
    }
}

public class BaseTest {
    private static void binarySearchTest() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        assert BinarySearch.binarySearch(arr, 1) == 0: "WA";
        assert BinarySearch.binarySearch(arr, 2) == 1: "WA";
        assert BinarySearch.binarySearch(arr, 5) == 4: "WA";
        assert BinarySearch.binarySearch(arr, 11) == -1: "WA";
        assert BinarySearch.binarySearch(arr, 0) == -1: "WA";
    }

    private static void superPowTest() {
        System.out.println(SuperPow.superPow(2, 5));
        System.out.println(SuperPow.superPow(2, 8));
        System.out.println(SuperPow.superPow(1, 8));
        System.out.println(SuperPow.superPow(0, 8));
        System.out.println(SuperPow.superPow(7, 0));
        System.out.println(SuperPow.superPow(-2, 3));
    }

    private static void gcdTest() {
        int[] a = {1, 3, 5, 7, 100, 1000};
        int[] b = {1, 3, 10, 14, 100, 1320};
        for (int i = 0; i < a.length; i++) {
            System.out.println("gcd0 of "+a[i]+" "+b[i]+" "+Gcd.gcd0(a[i], b[i]));
            System.out.println("gcd1 of "+a[i]+" "+b[i]+" "+Gcd.gcd1(a[i], b[i]));
        }
        //exception will be throw
        //System.out.println(Gcd.gcd0(1000, 1));
        //System.out.println(Gcd.gcd1(1000, -1));
    }

    private static void maxSubArrayTest() {
        int[][] arrs = {
                {1,},      //1
                {1, 2},    //3
                {1, 3, 4},    //8
                {1, -1, -5, 2, 3, 7}, //12
        };
        for (int[] a: arrs) {
            System.out.println(MaxSubArray.maxSubArray0(a));
            System.out.println(MaxSubArray.maxSubArray1(a));
            System.out.println(MaxSubArray.maxSubArray2(a));
            System.out.println(MaxSubArray.maxSubArray3(a));
        }

    }

    public static void main(String[] args) {
        //binarySearchTest();
        maxSubArrayTest();
    }
}
