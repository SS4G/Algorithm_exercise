package AlgorithmTraining.leetcode2nd;

import java.util.HashSet;

/**
 * Created by BUPT_SS4G on 2017/7/18.
 *
 */
class Leet003 {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> setC = new HashSet<>();
        int windowBegin = 0;
        int windowEnd = 0;
        int maxLen = 0;
        int curLen = 0;
        while (windowBegin < s.length()) {
            if (!setC.contains(s.charAt(windowBegin))) {
                setC.add(s.charAt(windowBegin));
                curLen = windowBegin - windowEnd + 1;
                //System.out.println("A" + windowBegin + "," + windowEnd + ":" + curLen);
                windowBegin ++;
            }
            else {
                curLen = windowBegin - windowEnd;
                //System.out.println("B" + windowBegin + "," + windowEnd + ":" + curLen);
                while (s.charAt(windowEnd) != s.charAt(windowBegin)) {
                    setC.remove(s.charAt(windowEnd));
                    windowEnd ++;
                }
                windowEnd++;
                windowBegin++;
            }
            //System.out.println(windowEnd + ":" + windowBegin);
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }
}
public class Leet003_t {
    public static void main(String[] args) {
        String str = "AAAAAA";
        str = "abcabcbb";
        str = "pwwkew";
        str = "";
        Leet003 leet = new Leet003();
        System.out.println(leet.lengthOfLongestSubstring(str));
    }
}
