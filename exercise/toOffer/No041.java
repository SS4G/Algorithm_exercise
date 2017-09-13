package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/11.
 */
import java.util.*;
class Solution041 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int curSum = 3;
        int lo = 1;
        int hi = 2;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while (lo < sum) {
            if (curSum < sum && hi < sum) {
                hi++;
                curSum += hi;
            }
            else if (curSum > sum) {
                curSum -= lo;
                lo++;
            }
            else if (curSum == sum) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = lo; i <= hi; i++) {
                    tmp.add(i);
                }
                if (tmp.size() >= 2) {
                    result.add(tmp);
                }
                curSum -= lo;
                lo++;
            }
            else {
                curSum -= lo;
                lo++;
            }
        }
        return result;
    }
}

public class No041 {
    public static void main(String[] args) {
        Solution041 s = new Solution041();
        System.out.println(s.FindContinuousSequence(3));
    }
}
