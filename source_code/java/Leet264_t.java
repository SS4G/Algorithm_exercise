package AlgorithmTraining.exercise.leetcode.java_src;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by BUPT_SS4G on 2017/6/28.
 */

class Leet264 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        TreeSet<Integer> curSet = new TreeSet<>();
        HashSet<Integer> curSethash = new HashSet<>();
        int res = -1;
        int tmp = 0;
        curSet.add(1);
        for (int i = 0; i < n; i++) {
            int curMin = curSet.first();
            for (int p: primes) {
                tmp = p * curMin;
                if (!curSethash.contains(tmp)) {
                    curSet.add(tmp);
                    curSethash.add(tmp);
                }
            }
            curSet.remove(curMin);
            curSethash.remove(curMin);
            res = curMin;
        }
        return res;
    }
}

public class Leet264_t {
    public static void main(String[] args) {
        Leet264 s = new Leet264();
        int[] primes = {2, 7, 13, 19};
        int n = 12;
        System.out.println(s.nthSuperUglyNumber(n, primes));
    }
}
