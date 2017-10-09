package AlgorithmTraining.exercise.leetcode2nd;

/**
 * Created by BUPT_SS4G on 2017/9/19.
 */
class Leet029 {
    public int divide(int dividend, int divisor) {
        // dividend / divisor
        if (divisor == 0)
            return Integer.MAX_VALUE;
        long dividendL = dividend == Integer.MIN_VALUE ? ((long)Integer.MAX_VALUE) + 1L : Math.abs(dividend);
        long divisorL = divisor == Integer.MIN_VALUE ? ((long)Integer.MAX_VALUE) + 1L : Math.abs(divisor);
        boolean dividendNegFlag = dividend < 0;
        boolean divisorNegFlag = divisor < 0;
        long cnt = 0;
        long rest = dividendL;
        long tmpVal;
        long tmpCnt;
        while (rest >= divisorL) {
            tmpVal = divisorL;
            tmpCnt = 1;
            while (rest >= tmpVal) {
                tmpVal <<= 1;
                tmpCnt <<= 1;
            }
            tmpVal >>= 1;
            tmpCnt >>= 1;
            rest -= tmpVal;
            cnt += tmpCnt;
        }
        if (cnt <= (long)Integer.MAX_VALUE) {
            //System.out.println("xx");
            //System.out.println(dividendNegFlag + ":" + divisorNegFlag);
            return dividendNegFlag == divisorNegFlag ? (int)cnt : -(int)cnt;
        }
        else if (cnt == (long)Integer.MAX_VALUE + 1L && dividendNegFlag != divisorNegFlag) {
            return Integer.MIN_VALUE;
        }
        else
            return Integer.MAX_VALUE;
    }
}

public class Leet029_t {
    public static void main(String[] args) {
        Leet029 leet = new Leet029();
        //int a = 3, b= 3;
        //int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
        int a = Integer.MIN_VALUE, b = 2;
        System.out.println(leet.divide(a, b));
        System.out.println(a / b);
        assert leet.divide(a, b) == a / b;
    }
}
