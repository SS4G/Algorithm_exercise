package AlgorithmTraining.leetcode2nd;

import java.util.HashMap;

/**
 * Created by 903 on 2017/7/21.
 *
 */

class Leet012 {
    public String intToRoman(int num) {
        String[][] vals = {
            {"","I","II","III","IV","V","VI","VII","VIII","IX"},
            {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
            {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
            {"","M","MM","MMM"},
        };
        StringBuilder sb = new StringBuilder(100);
        sb.append(vals[3][num / 1000]);
        num %= 1000;
        sb.append(vals[2][num / 100]);
        num %= 100;
        sb.append(vals[1][num / 10]);
        num %= 10;
        sb.append(vals[0][num]);
        //System.out.println(sb);
        return sb.toString();
    }
}

public class Leet012_t {
    public static void main(String[] args) {
        Leet012 leet = new Leet012();
        assert leet.intToRoman(1888).equals("MDCCCLXXXVIII"): "WA";
        assert leet.intToRoman(3999).equals("MMMCMXCIX"): "WA";
    }
}
