package AlgorithmTraining.leetcode2nd;

/**
 * Created by 903 on 2017/7/19.

 */
class Leet005 {
    public String longestPalindrome(String s) {
        s = preProcess(s);
        int maxLen = 0;
        int midIdx = -1;
        for (int i = 0; i < s.length(); i++) {
            int newRadius = expendFromMid(s, i);
            if (maxLen < newRadius) {
                maxLen = newRadius;
                midIdx = i;
            }
        }
        s = s.substring(midIdx - maxLen + 1, midIdx + maxLen);
        return deProcess(s);
    }

    private int expendFromMid(String s, int midIdx) {
        if (midIdx >= s.length()) {
            return 0;
        }
        int left = midIdx;
        int right = midIdx;
        int radius = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                radius ++;
                left --;
                right ++;
            }
            else
                break;
        }
        return radius;
    }

    private String preProcess(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 3);
        sb.append('#');
        for (int i = 0; i < s.length(); i ++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }

    private String deProcess(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i ++) {
            if ((i & 0x01) != 0)
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

public class Leet005_t {
    public static void main(String[] args) {
        Leet005 leet = new Leet005();
        String s = "babad";
        //s = "a";
        //s = "cbbd";
        s = "";
        System.out.println(leet.longestPalindrome(s));
    }
}
