package AlgorithmTraining.exercise.toOffer;

/**
 * Created by BUPT_SS4G on 2017/9/11.
 */
class Soulution041 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int divValue = 10;
        int eachBit = 1;
        int bits = getBits(n);
        int sum1s = 0;
        for (int j = 0; j < bits; j++) {
            sum1s += (n / divValue) * eachBit;
            divValue *= 10;
            eachBit *= 10;
        }
        //(bits - 1) * 10;
        return 0;
    }

    private int getBits(int n) {
        int i = 0;
        while (n > 0) {
            n /= 10;
            i++;
        }
        return i;
    }
}

public class No041 {
    public static void main(String[] args) {
        ;
    }
}
