package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/17.
 */
class Leet495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int wholeTime = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (i < timeSeries.length-1) {
                if (timeSeries[i + 1] - timeSeries[i] < duration)
                    wholeTime += timeSeries[i + 1] - timeSeries[i];
                else
                    wholeTime += duration;
            }
            else
                wholeTime += duration;
        }
        return wholeTime;
    }
}
public class Leet495_t {
    public static void main(String[] args) {
        Leet495 s = new Leet495();
        int[] ti = {1, 2};
        int res = s.findPoisonedDuration(ti, 2);
        System.out.println(res);
    }
}
