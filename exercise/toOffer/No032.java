package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/10.
 */
import java.util.*;
class Solution032 {
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    Solution032() {
        Arrays.sort(chars);
    }

    public ArrayList<String> Permutation(String str) {
        if (str.length() == 0)
            return new ArrayList<>();
        Map<Character, Integer> charCnt = new HashMap<>();
        for (char c : chars) {
            charCnt.put(c, 0);
        }

        for (char c : str.toCharArray()) {
            charCnt.put(c, charCnt.get(c) + 1);
        }
        LinkedList<Character> stack = new LinkedList<>();
        ArrayList<String> result = new ArrayList<>();
        genPermutation(charCnt, stack, result);
        return result;
    }

    private void genPermutation(Map<Character, Integer> charCnt, LinkedList<Character> stack, ArrayList<String> output) {
        boolean endFlag = true;
        for (Character c : chars) { // chars is sorted lexical order
            if (charCnt.get(c) > 0) {
                endFlag = false;
                stack.push(c);
                charCnt.put(c, charCnt.get(c) - 1);
                genPermutation(charCnt, stack, output);
                charCnt.put(c, charCnt.get(c) + 1);
                stack.pop();
            }
        }

        if (endFlag) {
            ArrayList<Character> oneResult = new ArrayList<>(stack);
            Collections.reverse(oneResult);
            StringBuilder sb = new StringBuilder();
            for (Character c : oneResult) {
                sb.append(c);
            }
            output.add(sb.toString());
        }
    }
}

public class No032 {
    public static void main(String[] args) {
        Solution032 s = new Solution032();
        System.out.println(s.Permutation("ABCA"));
    }
}
