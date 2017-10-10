package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/10/10.
 */
class Leet003x {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> curSet = new HashSet<>();
        if (s.length() == 0)
            return 0;
        int begin = 1;
        int end = 0;
        int maxLength = 1;
        curSet.add(s.charAt(0));
        while (begin < s.length() || end < s.length()) {
            if (begin == s.length()) {
                return Math.max(maxLength, begin - end);
            }
            else {
                if (!curSet.contains(s.charAt(begin))) {
                    curSet.add(s.charAt(begin));
                    begin++;
                    maxLength = Math.max(maxLength, begin - end);
                }
                else {
                    while (s.charAt(end) != s.charAt(begin)) {
                        curSet.remove(s.charAt(end));
                        end++;
                    }
                    end++;
                    begin++;
                }
            }
        }
        return maxLength;
    }
}

public class Leet003x_t {
    public static void main(String[] args) {
        Leet003x leet = new Leet003x();
        assert leet.lengthOfLongestSubstring("abcabcbb") == 3;
        assert leet.lengthOfLongestSubstring("bbbbb") == 1;
        assert leet.lengthOfLongestSubstring("pwwkew") == 3;
        assert leet.lengthOfLongestSubstring("") == 0;
    }
}
