package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/10.
 */
public class Leet029 {
    public static int divide(int dividend, int divisor) {

        boolean posFlag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

        long dividend_l = (long)dividend > 0 ? (long)dividend: -(long)dividend;
        long divisor_l = (long)divisor > 0 ? (long)divisor: -(long)divisor;
        // System.out.println(dividend_l+" "+divisor_l);
        if (dividend_l == 0 || dividend_l < divisor_l )
            return 0;
        //Integer.MAX_VALUE;

        long turnResult = 1;
        long restValue = dividend_l;
        long accumValue = 0;
        long nowValue = divisor;
        while (restValue >= divisor_l) {
            turnResult = 1;
            nowValue = divisor_l;
            while (nowValue <= restValue)
            {
                nowValue <<= 1;
                turnResult <<= 1;
            }
            nowValue >>= 1;
            turnResult >>= 1;
            accumValue += turnResult;
            restValue -= nowValue;
        }

        if (accumValue > Integer.MAX_VALUE && !posFlag )
            return Integer.MIN_VALUE;
        else if (accumValue > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return posFlag ? (int)accumValue:-(int)accumValue;
    }
    public static void main(String[] args) {
        System.out.println(divide(-2147483648,-1));
    }
}