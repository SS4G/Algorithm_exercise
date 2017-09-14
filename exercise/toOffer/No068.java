package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/14.
 */
class Solution068 {
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n == 0)
            return 0;
        int div = 10;
        int sum = 0;
        String bitsChar = Integer.toString(n);
        for (int i = 0; i < bitsChar.length(); i++) { // i [0, bitChars.length()] close interval
            int k = bitsChar.length() - 1 - i;
            div = getPow(10, i + 1);
            int completeNum = (n / div) * (div / 10);
            sum += completeNum;
            if (bitsChar.charAt(k) > '1') {
                sum += (div / 10);
            }
            else if (bitsChar.charAt(k) == '1') {
                sum += (n % (div / 10)) + 1;
            }
        }
        return sum;
    }

    private int getPow(int x, int exp) {
        int i = 0;
        int mul = 1;
        while (i++ < exp)
            mul *= x;
        return mul;
    }
}

public class No068 {
    public static void main(String[] args) {
        Solution068 s = new Solution068();
        assert 154 == s.NumberOf1Between1AndN_Solution(234); //(100) + (3 * 10) + (23 * 1 + 1);
        assert 1 == s.NumberOf1Between1AndN_Solution(1); // 1
        assert 0 == s.NumberOf1Between1AndN_Solution(0); // 0
        assert 57 == s.NumberOf1Between1AndN_Solution(123); //(24) + (20) + (12 * 1 + 1);
        assert 36 == s.NumberOf1Between1AndN_Solution(111); //(12) + (10 + 2) + 12
    }
}
