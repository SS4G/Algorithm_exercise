package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/18.
 */
class Leet402x {
    public String removeKdigits(String num, int k) {
        String tmp = num;
        for (int i = 0; i < k; i++) {
            tmp = remove1Digit(tmp);
            //System.out.println(tmp);
        }
        int i = 0;
        if (tmp.length() > 1) {
            i = 0;
            while (tmp.charAt(i) == '0' && i < tmp.length() - 1)
                i++;
            return tmp.substring(i);
        }
        return tmp;
    }

    public String remove1Digit(String num) {
        if (num.length() == 1) {
            return "0";
        }
        int i = 0;
        while (i < num.length()) {
            if (i == 0) {
                if (num.charAt(i) > num.charAt(i + 1)) {
                    return num.substring(1, num.length());
                }
            }
            else if (i == num.length() - 1) {
                return num.substring(0, num.length() - 1);
            }
            else {
                if (num.charAt(i) > num.charAt(i + 1)) {
                    return num.substring(0, i) + num.substring(i + 1, num.length());
                }
            }
            i++;
        }
        return "0";
    }
}

public class Leet402x_t {
    public static void main(String[] args) {
        Leet402x leet = new Leet402x();
        assert leet.removeKdigits("1432219", 3).equals("1219");
        assert leet.removeKdigits("10200", 1).equals("200");
        assert leet.removeKdigits("10", 2).equals("0");
        assert leet.removeKdigits("1000110", 2).equals("10");
    }
}
