package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
import java.util.*;
class Leet076x {
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCnt = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (!charCnt.containsKey(c))
                charCnt.put(c, 0);
            charCnt.put(c, charCnt.get(c) + 1);
        }
        String minWindow = "";
        int minWindowLen = Integer.MAX_VALUE;
        int bg = 0;
        int ed = 0;
        //ed <= bg
        while (ed <= bg || bg < s.length()) {
            if (satisfy(charCnt)) {
                charCnt.put(s.charAt(ed), charCnt.get(s.charAt(bg)) + 1);
            }
            else {
                charCnt.put(s.charAt(bg), charCnt.get(s.charAt(bg)) + 1);
            }
        }
    }

    private boolean satisfy(Map<Character, Integer> charCnt) {
        for (Character c : charCnt.keySet()) {
            if (charCnt.get(c) > 0)
                return false;
        }
        return true;
    }
}

public class Leet076x_t {
    public static void main(String[] args) {

    }
}
