package AlgorithmTraining.exercise.toOffer;

/**
 * Created by BUPT_SS4G on 2017/9/9.
 */
import java.util.*;
class Solution028 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int xorRes = 0;
        for (int i : array) {
            xorRes ^= i;
        }
        int mask = 0;
        for (int i = 0; i < 32; i++) {
             if ((xorRes & (0x00000001 << i)) != 0) {
                 mask = 0x00000001 << i;
                 break;
             }
        }
        List<Integer> set0 = new ArrayList<>();
        List<Integer> set1 = new ArrayList<>();
        for (int i : array) {
            if ((mask & i) != 0) {
                set0.add(i);
            }
            else {
                set1.add(i);
            }
        }
        int res0 = 0;
        int res1 = 0;
        for (int i : set0) {
            res0 ^= i;
        }
        for (int i : set1) {
            res1 ^= i;
        }
        num1[0] = res0;
        num2[0] = res1;
    }
}

public class No028 {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 4, 5, 6};
        int[] res0 = new int[1];
        int[] res1 = new int[1];
        Solution028 s = new Solution028();
        s.FindNumsAppearOnce(arr, res1, res0);
        System.out.println(res0[0] + ":" + res1[0]);
    }
}
