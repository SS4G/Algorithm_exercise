package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.Arrays;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
class Leet455x {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int sPtr = 0;
        int gPtr = 0;
        int cnt = 0;
        while (sPtr < s.length && gPtr < g.length) {
            if (g[gPtr] <= s[sPtr]) {
                cnt++;
                sPtr++;
                gPtr++;
            }
            else {
                sPtr++;
            }
        }
        return cnt;
    }
}

public class Leet455x_t {
    public static void main(String[] args) {
        int[] greedy = {1,2,3};
        int[] size = {1, 1};
        Leet455x leet = new Leet455x();
        assert leet.findContentChildren(greedy, size) == 1;
        greedy = new int[]{1, 2};
        size = new int[]{1, 2, 3};
        assert leet.findContentChildren(greedy, size) == 2;
    }
}
