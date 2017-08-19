package AlgorithmTraining.leetcode2nd;

/**
 * Created by 903 on 2017/7/20.

 */

class Leet007 {
    public int reverse(int x) {
        if (x == 0)
            return 0;
        boolean negFlag = false;
        long x0 = x;
        if (x0 < 0) {
            negFlag = true;
            x0 = -x0;
        }
        long[] buff = new long[13];
        int bitIdx = 0;
        while (x0 > 0) {
            buff[bitIdx] = x0 % 10;
            x0 = x0 / 10;
            bitIdx ++;
        }
        //for (long y: buff)
          //  System.out.println(y);
        //int res = 0;
        long result = 0;
        long pow = 1;
        int tail = buff.length - 1;
        while (buff[tail] == 0)
            tail --;
        for (int k = tail; k >= 0; k--) {
            result += buff[k] * pow;
            pow *= 10;
        }
        result = negFlag? -result: result;
        return 0x80000000 <= result && result <= 0x7fffffff? (int)result: 0;
    }

}
public class Leet007_t {
    public static void main(String[] args) {
        Leet007 leet = new Leet007();
        System.out.println(leet.reverse(0x7fffffff));
        System.out.println(leet.reverse(0));
    }
}
