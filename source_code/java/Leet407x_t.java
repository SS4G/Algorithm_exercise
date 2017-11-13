package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/10/17.
 */
class Leet407 {
    private final int STONE = 0x01;
    private final int AIR = 0x02;
    private final int EMPTY = 0;
    public int trapRainWater(int[][] heightMap) {

        int curHeight = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> added = new HashSet<>();
        for (int[] height : heightMap) {
            for (int i : height) {
                if (!added.contains(i)) {
                    pq.offer(i);
                    added.add(i);
                }
            }
        }
        int wholeVolume = 0;
        while (pq.size() >= 2) {// sea level increase algorithm
            while (pq.size() > 1 && pq.peek() == curHeight) { // get next different level
                pq.poll();
            }
            if (pq.size() == 1) // only one land
                break;
            int newLevel = pq.poll();
            int delta = newLevel - curHeight;
            //wholeArea += delta * getSegment(height, newLevel);
            curHeight = newLevel;
        }
        return 0;
    }
    //       bit 0:is soil bit 1: flowed by side water
    public int[][] genSection(int[][] heightMap, int height) {
        int[][] section = new int[heightMap.length][heightMap[0].length];
        for (int i = 0; i < section.length; i++) {
            for (int j = 0; j < section[0].length; j++) {
                section[i][j] |= heightMap[i][j] >= height ? STONE : EMPTY;
            }
        }
        return section;
    }

    public int getValidGrid(int[][] section, int XL, int YL) { //获取可以盛水的面积
        for (int x = 0; x < XL; x++) {
            bfsHelper(section, x, 0, XL, YL, AIR);
            bfsHelper(section, x, YL - 1, XL, YL, AIR);
        }

        for (int y = 0; y < YL; y++) {
            bfsHelper(section, 0, y, XL, YL, AIR);
            bfsHelper(section, XL - 1, y, XL, YL, AIR); //AIR是与周边相同的位置
        }

        //for ()
        return 0;
    }

    public void bfsHelper(int[][] matMark, int x, int y, int XL, int YL, int markVal) {
        if ((matMark[y][x] & markVal) == 0) {
            matMark[y][x] |= markVal; // mark first node
            ArrayList<int[]> fifo = new ArrayList<>();
            int rd = 0;
            fifo.add(new int[]{x, y});
            while (rd < fifo.size()) {
                int xPos = fifo.get(rd)[0];
                int yPos = fifo.get(rd)[1];
                List<int[]> adjs = getAdj(XL, YL, xPos, yPos);
                for (int[] adjPoint : adjs) {
                    int adjx = adjPoint[0];
                    int adjy = adjPoint[1];
                    if ((matMark[adjy][adjx] & markVal) == 0) { // just add unmarked
                        fifo.add(adjPoint);
                        matMark[adjy][adjx] |= markVal;
                    }
                }
                rd++;
            }
        }
    }

    private List<int[]> getAdj(int xL, int yL, int x, int y) {
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        if (x > 0)
            xs.add(x - 1);
        if (y > 0)
            ys.add(y - 1);
        if (x < xL - 1)
            xs.add(x + 1);
        if (y < yL - 1)
            ys.add(y + 1);
        List<int[]> adjs = new ArrayList<>();
        for (int x0 : xs) {
            int[] xx = new int[2];
            xx[0] = x0;
            xx[1] = y;
            adjs.add(xx);
        }

        for (int y0 : ys) {
            int[] yy = new int[2];
            yy[0] = x;
            yy[1] = y0;
            adjs.add(yy);
        }
        return adjs;
    }
}

public class Leet407x_t {
    public static void main(String[] args) {

    }
}
