package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/13.
 *
 */
import java.util.*;
class Solution050 {
    public int[] multiply(int[] A) {
        int multi = 1;
        Integer[] leftMul = new Integer[A.length];
        Integer[] rightMul = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            leftMul[i] = multi;
            multi *= A[i];
        }
        multi = 1;
        for (int i = A.length - 1; i >= 0; i--) {
            rightMul[i] = multi;
            multi *= A[i];
        }
        //System.out.println("left:" + Arrays.asList(leftMul));
        //System.out.println("right:" + Arrays.asList(rightMul));
        int[] b = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            b[i] = leftMul[i] * rightMul[i];
        }
        return b;
    }
}
public class No050 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Solution050 s = new Solution050();

        for (int i : s.multiply(arr)) {
            System.out.println(i);
        }
    }
}
