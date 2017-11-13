package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by 903 on 2017/8/5.
 *
 */
import java.util.*;

class Leet022 {
    public List<String> generateParenthesis(int n) {
        LinkedList<Character> stack = new LinkedList<>();
        ArrayList<String> output = new ArrayList<>();
        genHelper(n, 0, 0, stack, output);
        return output;
    }

    private void genHelper(int n, int leftCnt, int rightCnt,
                           LinkedList<Character> stack, ArrayList<String> output) {
        //System.out.println(leftCnt + ":" + rightCnt);
        if (leftCnt == rightCnt && leftCnt == n) {
            StringBuilder sb = new StringBuilder();
            for (Character c: stack) {
                sb.append(c);
            }
            output.add(sb.toString());
        }
        else {
            if (leftCnt > rightCnt) {
                if (leftCnt < n) {
                    stack.add('(');
                    genHelper(n, leftCnt + 1, rightCnt, stack, output);
                    stack.pollLast();
                }
                stack.add(')');
                genHelper(n, leftCnt, rightCnt + 1, stack, output);
                stack.pollLast();
            }
            else {
                stack.add('(');
                genHelper(n, leftCnt + 1, rightCnt, stack, output);
                stack.pollLast();
            }
        }
    }
}

public class Leet022_t {
    public static void main(String[] args) {
        Leet022 leet = new Leet022();
        List<String> res = leet.generateParenthesis(0);
        System.out.println(res);
    }
}
