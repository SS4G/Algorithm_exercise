package AlgorithmTraining.exercise.toOffer;

import java.util.ArrayList;

/**
 * Created by BUPT_SS4G on 2017/9/14.
 */
class Solution066 {
    public int GetUglyNumber_Solution(int index) {
        int idx = 1;
        if (index == 1)
            return 1;
        ArrayList<Integer> uglyBy2 = new ArrayList<>();
        ArrayList<Integer> uglyBy3 = new ArrayList<>();
        ArrayList<Integer> uglyBy5 = new ArrayList<>();
        uglyBy2.add(2);
        uglyBy3.add(3);
        uglyBy5.add(5);
        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;
        for (idx = 2; idx <= index; idx++) {
            int minUgly = Math.min(uglyBy2.get(idx2), Math.min(uglyBy3.get(idx3), uglyBy5.get(idx5)));
            if (minUgly == uglyBy2.get(idx2))
                idx2++;
            if (minUgly == uglyBy3.get(idx3))
                idx3++;
            if (minUgly == uglyBy5.get(idx5))
                idx5++;
            uglyBy2.add(minUgly * 2);
            uglyBy3.add(minUgly * 3);
            uglyBy5.add(minUgly * 5);
            if (idx == index)
                return minUgly;
        }

        return 0;
    }
}

public class No066 {
    public static void main(String[] args) {

    }
}
