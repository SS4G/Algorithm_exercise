package AlgorithmTraining.exercise.leetcode2nd;
import java.util.*;
/**
 * Created by 903 on 2017/8/4.
 *
 */

class Leet017 {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new LinkedList<String>();
        HashMap<Character, String> map = new HashMap<>();
        map.put('1', "");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        map.put('0', " ");
        StringBuilder sb = new StringBuilder();
        /*for (int idx = 0; idx < digits.length(); idx ++) {
            map.get(digits.charAt(idx));
        }*/
        List<String> output = new LinkedList<>();
        LinkedList<Character> stack = new LinkedList<>();
        genString(map, output, digits, 0, stack);
        return output;
    }

    public void genString(HashMap<Character, String> map, List<String> output,
                          String digits, int idx, LinkedList<Character> stack) {
        if (idx >= digits.length()) {
            StringBuilder sb = new StringBuilder(stack.size());
            for (Character c: stack) {
                sb.append(c);
            }
            output.add(sb.toString());
        }
        else {
            for (int j = 0; j < map.get(digits.charAt(idx)).length(); j ++) {
                stack.add(map.get(digits.charAt(idx)).charAt(j));
                genString(map, output, digits, idx + 1, stack);
                stack.pollLast();
            }
        }
    }
}
public class Leet017_t {
    public static void main(String[] args) {
        Leet017 leet = new Leet017();
        List<String> res = leet.letterCombinations("");
        for (String s: res) {
            System.out.println(s);
        }
    }
}
