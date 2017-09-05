package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by Administrator on 2017/5/13.
 *
 */
class Leet481{
    private boolean established = false;
    private final int LENGTH = 100001;
    private int[] magicString = new int[LENGTH+10]; // keep more space
    private int[] magicStringStatistic = new int[LENGTH];
    private int writeIn = 3;
    private int readOut = 2;
    public int magicalString(int n) {
        if (!established) {
            magicString[0] = 1;
            magicString[1] = 2;
            magicString[2] = 2;
            boolean isOne = true;
            int cnt = 1;
            magicStringStatistic[0] = 1;
            magicStringStatistic[1] = 1;
            magicStringStatistic[2] = 1;
            while (writeIn < LENGTH) {
                for (int p = 0; p < magicString[readOut]; p++) {
                    magicString[writeIn] = isOne ? 1 : 2;
                    cnt += isOne ? 1 : 0;
                    magicStringStatistic[writeIn] = cnt;
                    writeIn++;
                }
                isOne = !isOne;
                readOut ++;
            }
            established = true;
        }
        return n != 0 ? magicStringStatistic[n-1] : 0;
    }
}

public class Leet481_t {
    public static void main(String[] args) {
        Leet481 s = new Leet481();
        System.out.println(s.magicalString(1));
        System.out.println(s.magicalString(6));
        System.out.println(s.magicalString(11));

    }
}
