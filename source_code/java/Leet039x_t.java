package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/10/11.
 */

class Leet039x {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        HashMap<Integer, List<List<Integer>>> record = new HashMap<>();
        return combineRecure(record, target, candidates);
    }

    private List<List<Integer>> combineRecure(HashMap<Integer, List<List<Integer>>> partResRecord, int target, int[] candidates) {
        if (partResRecord.containsKey(target))
            return partResRecord.get(target);
        else {
            List<List<Integer>> tmpSumSet = new ArrayList<>();
            for (int cand : candidates) {
                List<List<Integer>> lastSumSet;
                List<Integer> oneResult;
                if (target - cand > 0) {
                    //System.out.println(target + ":" + cand);
                    lastSumSet = combineRecure(partResRecord, target - cand, candidates);
                    //System.out.println(lastSumSet);
                    if (lastSumSet.size() > 0) {
                        //System.out.println("tmpfile");
                        for (List<Integer> i : lastSumSet) {
                            if (i.get(i.size() - 1) <= cand) { //保证只有从小到大的一种排列
                                //System.out.println("tmpfile");
                                oneResult = new ArrayList<>(i);
                                oneResult.add(cand);
                                tmpSumSet.add(oneResult);
                            }
                        }
                    }
                } else if (target - cand == 0) {
                    oneResult = new ArrayList<>();
                    oneResult.add(cand);
                    tmpSumSet.add(oneResult);
                }
            }
            partResRecord.put(target, tmpSumSet);
            return tmpSumSet;
        }
    }
}

public class Leet039x_t {
    public static void main(String[] args) {
        Leet039x leet = new Leet039x();
        int[] cand = {2, 3, 5, 7};
        //int[] cand = {2, 3};
        System.out.println(leet.combinationSum(cand, 7));
    }
}
