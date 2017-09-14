package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/14.
 */
class Solution069 {
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) //invalid args
            return -1;
        if (n == 1)
            return 0;
        else {
            int last = 0;
            for (int i = 2; i <= n; i++) {
                last = (last + m) % i; // f(i, m)
            }
            return last;
        }
    }
}

public class No069 {
    public static void main(String[] args) {

    }
}
