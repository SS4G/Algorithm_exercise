package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by 903 on 2017/7/20.
 *
 */
class Leet009 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = (new Integer(x)).toString();
        int len = s.length();
        int l = 0;
        int r = len - 1;
        while (r >= l) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            r --;
            l ++;
        }
        return true;
    }
}
public class Leet009_t {
    public static void main(String[] args) {
        Leet009 leet = new Leet009();
        assert leet.isPalindrome(12321);
        assert leet.isPalindrome(12321);
        assert leet.isPalindrome(0);
    }
}
