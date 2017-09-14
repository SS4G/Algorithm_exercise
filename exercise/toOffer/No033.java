package AlgorithmTraining.exercise.toOffer;

import java.util.*;

/**
 * Created by G5501 on 2017/9/10.
 */

class Solution033 {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0)
            return 0;
        Map<Integer, Integer> cntMap = new HashMap<>();
        int curCnt;
        int curVal;
        curCnt = 1;
        curVal = array[0];
        cntMap.put(curVal, 1);
        for (int i = 1; i< array.length; i++) {
            if (cntMap.containsKey(array[i]))
                cntMap.put(array[i],cntMap.get(array[i]) + 1);
            else
                cntMap.put(array[i], 1);

            if (curCnt > 0) {
                if (curVal != array[i]) {
                    curCnt--;
                }
                else
                    curCnt++;
            }
            else {
                curVal = array[i];
                curCnt = 1;
            }
        }
        if (cntMap.get(curVal) > (array.length >> 1))
            return curVal;
        else
            return 0;

    }
}

public class No033 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 5, 5, };
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {};
        int[] arr3 = {1};
        int[] arr4 = {1, 2, 1, 2, 3, 2, 2, 4, 5, 2, 2};
        Solution033 s = new Solution033();
        System.out.println(s.MoreThanHalfNum_Solution(arr));
        System.out.println(s.MoreThanHalfNum_Solution(arr1));
        System.out.println(s.MoreThanHalfNum_Solution(arr2));
        System.out.println(s.MoreThanHalfNum_Solution(arr3));
        System.out.println(s.MoreThanHalfNum_Solution(arr4));
    }
}
