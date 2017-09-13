package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/10.
 */
import java.util.*;
class Soulution037 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        boolean found = false;
        int lo = 0;
        int hi = array.length - 1;
        int[] res0 = new int[2];
        int mul = Integer.MAX_VALUE;
        while (lo < hi) {
            if (array[lo] + array[hi] < sum) {
                lo++;
            }
            else if (array[lo] + array[hi] > sum) {
                hi--;
            }
            else {
                if (mul > array[lo] * array[hi]) {
                    found = true;
                    res0[0] = array[lo];
                    res0[1] = array[hi];
                    mul = array[lo] * array[hi];
                }
                hi--;
            }
        }
        if (!found)
            return new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(res0);
        result.add(res0[0]);
        result.add(res0[1]);
        return result;
    }
}

public class No037 {
    public static void main(String[] args) {

    }
}
