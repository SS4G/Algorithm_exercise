package AlgorithmTraining.G55Utils.Java;

/**
 *
 * Created by BUPT_SS4G on 2017/7/17.
 */
public class MathUtil {
    public static int gcd(int a, int b) {
        if (a < b) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        assert a >= b : "a is smaller than b, invalid input";
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }
    public static void main(String[] args) {
        System.out.println(gcd(1, 2));
        System.out.println(gcd(4, 2));
        System.out.println(gcd(2, 2));
        System.out.println(gcd(1024, 3 * 512));
    }
}