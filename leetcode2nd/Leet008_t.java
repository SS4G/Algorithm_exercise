package AlgorithmTraining.leetcode2nd;

/**
 * Created by 903 on 2017/7/20.
 *
 */
import java.util.regex.*;

class Leet008 {
    public int myAtoi(String str) {
        Pattern checkPat = Pattern.compile("\\s*[\\+\\-]?\\d+");
        Pattern pat = Pattern.compile("[\\+\\-]?\\d+");
        Matcher m0 = pat.matcher(str);
        Matcher m1 = checkPat.matcher(str);
        if (!m1.lookingAt()) {
            return 0;
        }
        if (m0.find()) {
            String validPart = m0.group(0);
            return validStr(validPart);
        }
        else {
            return 0;
        }
    }

    private int validStr(String s) {
        boolean negFlag = false;
        String numPart;
        if (s.charAt(0) == '+') {
            negFlag = false;
            numPart = s.substring(1);
        }
        else if (s.charAt(0) == '-') {
            negFlag = true;
            numPart = s.substring(1);
        }
        else {
            negFlag = false;
            numPart = s;
        }
        if (numPart.length() > 12)
            return negFlag? Integer.MIN_VALUE: Integer.MAX_VALUE;

        long numVal = Long.parseLong(numPart);
        if (numVal <= 0x7fffffffL) {
            return negFlag? (int)(-numVal): (int)numVal;
        }
        else if (numVal == 0x80000000L) {
            return negFlag? (int)(-numVal): Integer.MAX_VALUE;
        }
        else {
            return negFlag? Integer.MIN_VALUE: Integer.MAX_VALUE;
        }
    }
}

public class Leet008_t {
    public static void main(String[] args) {
        Leet008 leet = new Leet008();
        System.out.println(leet.myAtoi("123456"));
        System.out.println(leet.myAtoi("+123456"));
        System.out.println(leet.myAtoi("-123456"));
        System.out.println(leet.myAtoi("  123456ppp"));
        System.out.println(leet.myAtoi("  0"));
        System.out.println(leet.myAtoi(" -2147483649"));
        System.out.println(leet.myAtoi(" +-2147483649"));
    }
}
