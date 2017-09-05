package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by 903 on 2017/7/22.
 *
 */

class Leet014 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String s: strs)
            minLen = Math.min(minLen, s.length());
        if (minLen == 0)
            return "";
        StringBuilder sb = new StringBuilder(1000);
        char std = '-';
        for (int i = 0; i < minLen; i ++) {
            std = strs[0].charAt(i);
            boolean flag = true;
            for (String s0: strs) {
                if (s0.charAt(i) != std) {
                    flag = false;
                    //System.out.println(s0.charAt(i) + ":" + i);
                }
            }
            if (flag)
                sb.append(std);
            else
                break;
        }
        return sb.toString();
    }
}

public class Leet014_t {
    public static void main(String[] args) {
        Leet014 lt = new Leet014();
        String[] sarr = {"aca","cba"};
        System.out.println(lt.longestCommonPrefix(sarr));
    }
}
