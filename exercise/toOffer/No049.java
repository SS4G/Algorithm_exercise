package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/13.
 */
import java.util.regex.*;
class Solution049 {
    public boolean isNumeric(char[] str) {
        String strVal = new String(str);
        Pattern[] pats = {
            Pattern.compile("[\\+\\-]?\\d+$"),
            Pattern.compile("[\\+\\-]?\\d+[Ee][\\+\\-]?\\d+$"),
            Pattern.compile("[\\+\\-]?\\d*\\.\\d+[Ee][\\+\\-]?\\d+$"),
            Pattern.compile("[\\+\\-]?\\d*\\.\\d+$"),
        };

        boolean res = false;
        for (Pattern pat : pats) {
            res |= pat.matcher(strVal).lookingAt();
        }
        return res;
    }
}
public class No049 {
    public static void main(String[] args) {
        Solution049 s = new Solution049();
        assert s.isNumeric("11".toCharArray());
        assert s.isNumeric("+11".toCharArray());
        assert s.isNumeric("-11".toCharArray());
        assert s.isNumeric("-11.11".toCharArray());
        assert s.isNumeric("+11.22".toCharArray());
        assert s.isNumeric("+11e2".toCharArray());
        assert s.isNumeric("+11E-4".toCharArray());
        assert s.isNumeric("-11E+4".toCharArray());
        assert !s.isNumeric("-11a+4".toCharArray());
    }
}
