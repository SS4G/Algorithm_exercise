package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.PriorityQueue;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */
class Leet042x {
    public int trap(int[] height) {
        int curHeight = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : height) {
            pq.offer(i);
        }
        int wholeArea = 0;
        while (pq.size() >= 2) {// sea level increase algorithm
            while (pq.size() > 1 && pq.peek() == curHeight) { // get next different level
                pq.poll();
            }
            if (pq.size() == 1) // only one land
                break;
            int newLevel = pq.poll();
            int delta = newLevel - curHeight;
            wholeArea += delta * getSegment(height, newLevel);
            curHeight = newLevel;
        }
        return wholeArea;
    }

    public int getSegment(int[] height, int level) {
        int lastPos = -1;
        int sum = 0;
        for (int i = 0; i < height.length; i++){
            if (level <= height[i]) {
                if (lastPos >= 0) {
                    sum += i - lastPos - 1;
                    lastPos = i;
                }
                else
                    lastPos = i;
            }
        }
        return sum;
    }
}

public class Leet042x_t {
    public static void main(String[] args) {
        Leet042x leet = new Leet042x();
        int[] arr = {0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0};
        //arr = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        //System.out.println(leet.getSegment(arr, 1));
        leet.trap(arr);
    }
}
