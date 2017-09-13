package AlgorithmTraining.exercise.toOffer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by szh-920 on 17-9-11.
 */

class Solution039 {
    public int InversePairs(int [] array) {
        return (int)(mergeSort(array, 0, array.length) % 1000000007L);
    }

    private long mergeSort(int[] arr, int begin, int end) {
        //begin include
        //end exclude
        if (end - begin <= 1) {
            return 0L;
        }
        int midPoint = (begin + end - 1) >> 1;
        long leftCnt = mergeSort(arr, begin, midPoint + 1);
        long rightCnt = mergeSort(arr, midPoint + 1, end);
        int[] auxArr = new int[end - begin];
        int ptr0 = begin;
        int ptr1 = midPoint + 1;
        int ptr2 = 0;
        long cnt = leftCnt + rightCnt;
        while (ptr0 < midPoint + 1 && ptr1 < end) {
            if (arr[ptr0] > arr[ptr1]) {
                cnt += (midPoint + 1 - ptr0);
                auxArr[ptr2++] = arr[ptr1++];
            }
            else {
                auxArr[ptr2++] = arr[ptr0++];
            }
        }
        while (ptr0 < midPoint + 1) {
            auxArr[ptr2++] = arr[ptr0++];
        }
        while (ptr1 < end) {
            auxArr[ptr2++] = arr[ptr1++];
        }
        System.arraycopy(auxArr, 0, arr, begin, end - begin);
        return cnt;
    }
}

public class No039 {
    public static void main(String[] args) {
        Solution039 s = new Solution039();
        int[] arr = {1, 2, 3, 0};
        //int[] arr = {1, 0};
        //int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        System.out.println(s.InversePairs(arr));
        ArrayList<Integer> list = new ArrayList<>();
        for (int i: arr) {
            list.add(i);
        }
        System.out.println(list);
    }
}
