package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by BUPT_SS4G on 2017/9/19.
 */
class Leet028 {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (haystack.length() == 0 && needle.length() != 0)
            return -1;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return i;
        }
        return -1;
    }
}

public class Leet028_t {
    public static void main(String[] args) {
        Leet028 leet = new Leet028();
        System.out.println(leet.strStr("ABCABXCDE", "ABX"));
        assert leet.strStr("ABCABXCDE", "ABX") == 3;
        assert leet.strStr("", "ABX") == -1;
        assert leet.strStr("ABCD", "") == 0;
        assert leet.strStr("mississippi","a") == -1;
    }
}
