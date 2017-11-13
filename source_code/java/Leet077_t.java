package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/18.
 */
import java.util.*;
class Leet077 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> finalResult = new LinkedList<List<Integer>>();
        if (n < k) {
            return finalResult;
        }
        Integer[] stack = new Integer[k];
        generateCombine(finalResult, k, n, stack, 0, 1);
        return finalResult;
    }

    private void generateCombine(List<List<Integer>> combineResult, int width, int maxLimit, Integer[] stack, int nowPos, int st) {
        for (int i = st; i <= maxLimit; i++) {
            stack[nowPos] = i;
            //System.out.println(nowPos+","+i);
            if (nowPos == width - 1) {
                List<Integer> copyRes = new ArrayList<Integer>(width);
                for (int index = 0; index < width; index++)
                    copyRes.add(stack[index]);
                combineResult.add(copyRes);
            }
            else
                generateCombine(combineResult, width, maxLimit, stack, nowPos+1, i+1);
        }
    }
}
public class Leet077_t {
    public static void main(String[] args) {
        Leet077 s = new Leet077();
        List<List<Integer>> p = s.combine(4, 2);
        for (List<Integer> k : p) {
            for (Integer i : k) {
                System.out.print(i+",");
            }
            System.out.println("");
        }
    }
}
