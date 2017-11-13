package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by G5501 on 2017/9/18.
 */
//aborted!!!
import java.util.*;
class CharElement {
    public char ch;
    public int leastlen = -1;
    public boolean fixed = false;
    CharElement(char ch) {
        this.ch = ch;
    }

    public String toString() {
        return ch + ":" + leastlen + ":" + fixed + " ";
    }
}
class Leet010_2 {
    public boolean isMatch(String s, String p) {
        ArrayList<CharElement> pattern = new ArrayList<>();
        return false;
    }

    public boolean matchRecursive(ArrayList<CharElement> str, int strStart, ArrayList<CharElement> pat, int patStart) {
        int strIdx = strStart;
        int patIdx = patStart;
        while (strIdx < str.size() && patIdx < pat.size()) {
            char patCh = pat.get(patIdx).ch;
            char strCh = str.get(strIdx).ch;
            boolean patFixed = pat.get(patIdx).fixed;
            int patLength = pat.get(patIdx).leastlen;
            int strLength = str.get(strIdx).leastlen;
            if (patCh != '.') {
                if (strCh == patCh && ((patFixed && strLength == patLength) || (!patFixed && strLength >= patLength))) {
                    strIdx++;
                    patIdx++;
                }
                else if (strCh != patCh && patFixed && patLength == 0) {
                    patIdx++;
                }
                else {
                    return false;
                }
            }
            else {
                if (patFixed) {
                    strLength -= patLength;
                    if (strLength == 0) {
                        strIdx++;
                    }
                    else {
                        str.get(strIdx).leastlen--;
                    }
                }
            }
        }
        // $ todo handle the x* at the end of pattern
        return true;
    }

    public ArrayList<CharElement> preprocessPattern(String pat) {
        ArrayList<CharElement> pattern = new ArrayList<>();
        char lastChar = '#';
        CharElement che = new CharElement('-');
        for (int i = 0; i < pat.length(); i++) {
            if (pat.charAt(i) == lastChar) {
                if (i < pat.length() - 1 && pat.charAt(i + 1) == '*') {
                    che.fixed = false;
                    i++; // jump the *
                }
                else {
                    che.leastlen++;
                }
            }
            else {
                che = new CharElement(pat.charAt(i));
                if (i < pat.length() - 1 && pat.charAt(i + 1) == '*') {
                    che.leastlen = 0;
                    che.fixed = false;
                    che.ch = pat.charAt(i);
                    i++; // jump the *
                }
                else {
                    che.fixed = true;
                    che.leastlen = 1;
                    che.ch = pat.charAt(i);
                }
                lastChar = che.ch;
                pattern.add(che);
            }
        }
        return pattern;
    }

    public ArrayList<CharElement> preprocessText(String str) {
        ArrayList<CharElement> text = new ArrayList<>();
        char lastChar = '#';
        CharElement che = new CharElement('-');
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == lastChar) {
                che.leastlen++;
            }
            else {
                che = new CharElement(str.charAt(i));
                che.leastlen = 1;
                lastChar = che.ch;
                text.add(che);
            }
        }
        return text;
    }
}

public class Leet010_2_t {
    public static void main(String[] args) {

    }
}
