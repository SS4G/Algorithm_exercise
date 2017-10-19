package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
import java.util.*;
class Leet491x {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> uniqueSet = new HashSet<>();
        for (int i : nums) {
            List<List<Integer>> tmpRes = new ArrayList<>();
            for (List<Integer> oneRes : res) {
                if (oneRes.get(oneRes.size() - 1) <= i) {
                    List<Integer> newTmpRes = new ArrayList<>(oneRes);
                    newTmpRes.add(i);
                    if (!uniqueSet.contains(newTmpRes)) {
                        uniqueSet.add(newTmpRes);
                        tmpRes.add(newTmpRes);
                    }
                }
            }
            List<Integer> oneElementSeq = new ArrayList<>();
            oneElementSeq.add(i);
            if (!uniqueSet.contains(oneElementSeq)) {
                uniqueSet.add(oneElementSeq);
                tmpRes.add(oneElementSeq);
            }
            res.addAll(tmpRes);
        }
        List<List<Integer>> finalResult = new ArrayList<>();
        for (List<Integer> seq : uniqueSet) {
            if (seq.size() > 1)
                finalResult.add(seq);
        }
        return finalResult;
    }
}

public class Leet491x_t {
    public static void main(String[] args) {
        Leet491x leet = new Leet491x();
        int[] arr = {4, 6, 7, 7};
        System.out.println(leet.findSubsequences(arr));
    }
}
