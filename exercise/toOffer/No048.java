package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/13.
 */
import java.util.regex.*;
class Solution048 {
    public int StrToInt(String str) {
        //123 //-123 //+123
        if (str.length() <= 0)
            return 0;
        if (str.equals("-2147483648"))
            return -2147483648;
        Pattern int0Pattern = Pattern.compile("[\\+\\-]?(\\d+)$");
        Pattern int1Pattern = Pattern.compile("[\\+\\-]?(\\d+)\\.(\\d+)[eE](\\d+)$");
        Pattern int2Pattern = Pattern.compile("[\\+\\-]?(\\d+)[eE](\\d+)$");
        Matcher mat0 = int0Pattern.matcher(str);
        Matcher mat1 = int1Pattern.matcher(str);
        Matcher mat2 = int2Pattern.matcher(str);
        boolean negFlag = isNeg(str);
        if (mat0.lookingAt()) {
            String val = mat0.group(1);
            return negFlag ? -Integer.parseInt(val): Integer.parseInt(val);
        }
        else if (mat1.lookingAt()) {
            String pointPart = mat1.group(2);
            int i = pointPart.length() - 1;
            int zeroCnt = 0;
            while (str.charAt(i) == '0') {
                zeroCnt++;
            }
            if (pointPart.length() - zeroCnt > Integer.parseInt(mat1.group(3))) {
                return 0;
            }
            else {
                pointPart = mat1.group(2) + "000000000000000";
                String val = mat1.group(1) + pointPart.substring(0, Integer.parseInt(mat1.group(3)));
                return negFlag ? -Integer.parseInt(val): Integer.parseInt(val);
            }
        }
        else if (mat2.lookingAt()) {
            String baseValue = mat2.group(1);
            int val = Integer.parseInt(baseValue) * getPow(10, Integer.parseInt(mat2.group(2)));
            return negFlag ? -val: val;
        }
        else {
            return 0;
        }
            //1e6 //1.3e6 //+1.3e6 //-1.3e6
    }

    private int toInt(String str) {
        int pow = 1;
        int value = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c <= '9' && c >= '0') {// as digit
                value += ((c - '0') * pow);
                pow *= 10;
            }
        }
        return value;
    }

    private int getPow(int x, int exp) {
        int mulResult = 1;
        for (int i = 0; i < exp; i++) {
            mulResult *= x;
        }
        return mulResult;
    }

    private boolean isNeg(String str) {
        boolean negFlag = false;
        if (str.charAt(0) == '+') {
            negFlag = false;
        }
        else if (str.charAt(0) == '-') {
            negFlag = true;
        }
        return negFlag;
    }
}

public class No048 {
    public static void main(String[] args) {
        Solution048 s = new Solution048();
        //System.out.println(s.StrToInt("1023"));
        //System.out.println(s.StrToInt("1"));
        //System.out.println(s.StrToInt("-123"));
        //System.out.println(s.StrToInt("+123"));
        //System.out.println(s.StrToInt("+1a23"));
        //System.out.println(s.StrToInt("10e3"));
        //System.out.println(s.StrToInt("104e3"));
        //System.out.println(s.StrToInt("104e0"));
        //System.out.println(s.StrToInt("+104e0"));
        //System.out.println(s.StrToInt("-104E1"));
        System.out.println(s.StrToInt("-123.4567E3"));
    }
}
