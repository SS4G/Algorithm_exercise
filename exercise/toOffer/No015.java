package AlgorithmTraining.exercise.toOffer;
import java.lang.*;
import java.util.*;
class Solution15 {
    public void reOrderArray(int [] array) {
        int oddCnt = 0;
        for (int i : array) {
            if ((i & 0x01) != 0) {
                oddCnt += 1;
            }
        }
        int[] tmp = new int[array.length];
        int oddPtr = 0;
        int evenPtr = oddCnt;
        for (int i : array) {
            if ((i & 0x01) != 0) {
                tmp[oddPtr] = i;
                oddPtr += 1;
            }
            else {
                tmp[evenPtr] = i;
                evenPtr += 1;
            }
        }
        System.arraycopy(tmp, 0, array, 0, array.length);
    }
}

public class No015 {
    public static void main(String[] args) {
        int[] arr0 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr1 = {1, };
        int[] arr2 = {};
        Solution15 s = new Solution15();
        s.reOrderArray(arr0);
        List x = Arrays.asList(arr0);
        System.out.println(x);
        s.reOrderArray(arr1);
        System.out.println(Arrays.asList(arr1));
        s.reOrderArray(arr2);
        System.out.println(Arrays.asList(arr2));
    }
}