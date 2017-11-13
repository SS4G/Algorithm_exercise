package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
import java.util.*;
class Leet076x {
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCnt = new HashMap<>();
        for (char c : t.toCharArray()) {//build dict
            if (!charCnt.containsKey(c))
                charCnt.put(c, 0);
            charCnt.put(c, charCnt.get(c) + 1);
        }
        String minWindow = "";
        int minWindowLen = Integer.MAX_VALUE;
        int bg = 1;
        int ed = 0;

        //ed <= bg
        char c = s.charAt(0);
        if (charCnt.containsKey(c)) {
            charCnt.put(c, charCnt.get(c) - 1);
        }

        while (ed < s.length()) {
            if (satisfy(charCnt)) {
                //System.out.println(ed + ":" + bg);
                if (bg - ed < minWindowLen) {
                    minWindow = s.substring(ed, bg);
                    minWindowLen = minWindow.length();
                }

                c = s.charAt(ed);
                if (charCnt.containsKey(c)) {
                    charCnt.put(c, charCnt.get(c) + 1); //ed inc
                }
                ed++;
            }
            else {
                if (bg >= s.length())
                    break;
                c = s.charAt(bg);
                if (charCnt.containsKey(c)) {
                    charCnt.put(c, charCnt.get(c) - 1);
                }
                bg++;
            }
        }
        return minWindow;
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
        String s = "ADOBECODEBANC";
        String t = "ABC";
        s = "cabwefgewcwaefgcf";
        t = "cae";
        Leet076x leet = new Leet076x();
        System.out.println(leet.minWindow(s, t));
    }
}
