package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by G5501 on 2017/10/15.
 */
class Leet290x {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> patMap = new HashMap<>();
        Map<String, Character> strMap = new HashMap<>();
        String[] strs = str.trim().split("\\s+");
        if (pattern.length() != strs.length)
            return false;
        for (int i = 0; i < strs.length; i++) {
            if (!patMap.containsKey(pattern.charAt(i))) {
                if (strMap.containsKey(strs[i]) && !strMap.get(strs[i]).equals(pattern.charAt(i))) {
                    return false;
                }
                patMap.put(pattern.charAt(i), strs[i]);
                strMap.put(strs[i], pattern.charAt(i));
            }
            else {
                if (!patMap.get(pattern.charAt(i)).equals(strs[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class Leet290x_t {
    public static void main(String[] args) {
        String pattern = "aabbcac";
        String str0 = "dog dog cat cat mi dog mi";
        String str1 = "dog dog cat cbt mi dog mi";
        String str2 = "dog dog dog dog dog dog dog";
        Leet290x leet = new Leet290x();
        assert leet.wordPattern(pattern, str0);
        assert !leet.wordPattern(pattern, str1);
        assert !leet.wordPattern(pattern, str2);
    }
}
