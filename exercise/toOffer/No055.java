package AlgorithmTraining.exercise.toOffer;

import java.util.*;
class Solution055 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int curSum = 3;
        int lo = 1;
        int hi = 2;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while (lo < sum) {
            if (curSum < sum && hi < sum) {
                hi++;
                curSum += hi;
            } else if (curSum > sum) {
                curSum -= lo;
                lo++;
            } else if (curSum == sum) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = lo; i <= hi; i++) {
                    tmp.add(i);
                }
                if (tmp.size() >= 2) {
                    result.add(tmp);
                }
                curSum -= lo;
                lo++;
            } else {
                curSum -= lo;
                lo++;
            }
        }
        return result;
    }
}