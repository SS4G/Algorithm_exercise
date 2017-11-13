package AlgorithmTraining.exercise.leetcode2nd;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by 903 on 2017/8/5.
 */
class Leet020 {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        for (int i = 0; i < s.length(); i ++) {
            if (stack.size() <= 0 ||
               (!map.containsKey(s.charAt(i))) ||
               stack.getLast() != map.get(s.charAt(i))) {
                stack.add(s.charAt(i));
            }
            else {
                stack.pollLast();
            }
        }
        System.out.println(stack);
        return !(stack.size() > 0);
    }
}

public class Leet020_t {
    public static void main(String[] args) {
        String test = "{[][]{}()}";
        test = "{](())";
        Leet020 leet = new Leet020();
        System.out.println(leet.isValid(test));
    }
}
