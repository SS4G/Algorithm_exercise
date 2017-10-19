package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
class Leet459x {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.length() % i == 0) {
                if (validString(s.toCharArray(), s.substring(0, i).toCharArray()))
                    return true;
            }
        }
        return false;
    }

    public boolean validString(char[] s, char[] pattern) {
        for (int i = 0; i < s.length; i += pattern.length) {
            for (int j = 0; j < pattern.length; j++) {
                if (s[i + j] != pattern[j])
                    return false;
            }
        }
        return true;
    }
}

public class Leet459x_t {
    public static void main(String[] args) {
        String s = "abcabcabcabc";
        Leet459x leet = new Leet459x();
        assert leet.repeatedSubstringPattern(s);
        s = "aba";
        assert !leet.repeatedSubstringPattern(s);
        s = "abab";
        assert leet.repeatedSubstringPattern(s);
    }
}
