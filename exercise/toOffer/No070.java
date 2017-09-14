package AlgorithmTraining.exercise.toOffer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by G5501 on 2017/9/14.
 */

class StringElement implements Comparable<StringElement> {
    String str;
    StringElement(String s) {
        str = s;
    }

    public int compareTo(StringElement o) {
        String so = o.str;
        int idx = 0;
        while (idx < Math.min(str.length(), so.length())) {
            if (str.charAt(idx) < o.str.charAt(idx)) {
                return -1;
            }
            else if (str.charAt(idx) > o.str.charAt(idx)) {
                return 1;
            }
            else {
                if (idx == str.length() - 1 && idx == so.length() - 1) {
                    return 0;
                }
                else if (idx == str.length() - 1) {
                    return -subCompare(so, str);
                }
                else if (idx == so.length() - 1) {
                    return subCompare(str, so);
                }
                else {
                    idx++;
                }
            }
        }
        return 0;
    }


    public int subCompare(String a, String b) { //String a.length >= b.length
        assert a.length() >= b.length();
        int startIdx = 0;
        while (a.substring(startIdx, Math.min(a.length(), b.length() + startIdx)).compareTo(b) == 0) {
               startIdx += b.length();
        }
        int idx2 = startIdx;
        while (idx2 < Math.min(a.length(), b.length() + startIdx)) {
            if (a.charAt(idx2) == b.charAt(idx2 - startIdx)) {
                idx2++;
            }
            else if (a.charAt(idx2) > b.charAt(idx2 - startIdx)) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if (idx2 == a.length() && idx2 == b.length() + startIdx) {
            return 0;
        }
        else if (idx2 == a.length()) {
            if (a.charAt(0) > b.charAt(idx2 - startIdx))
                return 1;
            else if (a.charAt(0) < b.charAt(idx2 - startIdx))
                return -1;
            else
                return 0;
        }
        else if (idx2 == b.length() + startIdx) {
            if (b.charAt(0) > a.charAt(idx2))
                return -1;
            else if (b.charAt(0) < a.charAt(idx2))
                return 1;
            else
                return 0;
        }
        else
            return 0;
    }
}

class Solution070 {
    public String PrintMinNumber(int [] numbers) {
        ArrayList<StringElement> arr = new ArrayList<>();
        for (int i : numbers) {
            arr.add(new StringElement(Integer.toString(i)));
        }
        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (StringElement e : arr) {
            sb.append(e.str);
        }
        return sb.toString();
    }
}

public class No070 {
    public static void main(String[] args) {
        //String s = "0123456789";
        //System.out.println(s.substring(1, 5));
        //StringElement e = new StringElement("");
        //System.out.println(e.subCompare("123123123123123", "123"));
        //System.out.println(e.subCompare("123123123123123", "123"));
        //System.out.println(e.subCompare("123123123123123", "1232"));
        //System.out.println(e.subCompare("123123123123123", "1233"));
        //System.out.println(e.subCompare("123123123123123", "1235"));
        Solution070 ss = new Solution070();
        //int[] numbers = {3, 32, 321};
        int[] numbers = {3334,3,3333332};
        System.out.println(ss.PrintMinNumber(numbers));
        //StringElement se0 = new StringElement("3");
        //StringElement se1 = new StringElement("3334");
        //System.out.println(se0.compareTo(se1));
        //System.out.println(se0.subCompare("3334", "3"));
    }
}
