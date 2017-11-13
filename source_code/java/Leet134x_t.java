package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/13.
 */

class Leet134x {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] purerest = new int[gas.length];
        for (int i = 0; i < purerest.length; i++) {
            purerest[i] = gas[i] - cost[i];
        }

        int total = 0;
        int stIdx = 0;
        int edIdx = 0;
        boolean flag = false;
        while (true) {
            if (total < 0) {
                if (flag)
                    return -1;
                stIdx = edIdx;
                while (purerest[stIdx] < 0) {
                    stIdx++;
                    if (stIdx >= gas.length)
                        return -1;
                }
                total = purerest[stIdx];
                flag = flag || (stIdx + 1 >= gas.length);
                edIdx = (stIdx + 1) % gas.length;
            }
            else {
                total += purerest[edIdx];
                flag = flag || (edIdx + 1 >= gas.length);
                edIdx = (edIdx + 1) % gas.length;
                if (total >= 0 && edIdx == stIdx)
                    return stIdx;
            }
        }
    }
}

public class Leet134x_t {
    public static void main(String[] args) {
        Leet134x leet = new Leet134x();

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] purerest = new int[gas.length];
        for (int i = 0; i < purerest.length; i++) {
            purerest[i] = gas[i] - cost[i];
        }

        int total = 0;
        int stIdx = 0;
        int edIdx = 0;
        boolean flag = false;
        while (true) {
            if (total < 0) {
                if (flag)
                    return -1;
                stIdx = edIdx;
                while (purerest[stIdx] < 0) {
                    stIdx++;
                    if (stIdx >= gas.length)
                        return -1;
                }
                total = purerest[stIdx];
                flag = flag || (stIdx + 1 >= gas.length);
                edIdx = (stIdx + 1) % gas.length;
            }
            else {
                total += purerest[edIdx];
                flag = flag || (edIdx + 1 >= gas.length);
                edIdx = (edIdx + 1) % gas.length;
            }
        }
    }
}
