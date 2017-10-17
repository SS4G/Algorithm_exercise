package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.ArrayUtil;

import java.util.*;

/**
 * Created by G5501 on 2017/10/17.
 */
class Leet407x2 {
    private final int STONE = 0x01;
    private final int AIR = 0x02;
    private final int EMPTY = 0;
    public int trapRainWater(int[][] heightMap) {
        final int YL = heightMap.length;
        if (YL == 0)
            return 0;
        final int XL = heightMap[0].length;
        if (XL == 0)
            return 0;

        Set<Integer> added = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] height : heightMap) {
            for (int i : height) {
                if (!added.contains(i)) {
                    pq.offer(i);
                    added.add(i);
                }
            }
        }

        int curHeight = 0;

        int wholeVolume = 0;
        //System.out.println(pq);
        while (pq.size() > 0) {// sea level increase algorithm
            int newLevel = pq.poll();
            int delta = newLevel - curHeight;
            int[][] section = getSection(heightMap, newLevel, XL, YL);
            //System.out.println("delta=" + delta + "curHeight=" + curHeight);
            //ArrayUtil.showArr2D(section);
            wholeVolume += delta * getArea(section, XL, YL);
            curHeight = newLevel;
        }
        return wholeVolume;
    }

    public int[][] getSection(int[][] heightMap, int curHeight, int XL, int YL) {
        int[][] section = new int[YL][XL];
        for (int x = 0; x < XL; x++) {
            for (int y = 0; y < YL; y++) {
                    section[y][x] = heightMap[y][x] >= curHeight ? STONE : EMPTY;
            }
        }
        return section;
    }

    public int getArea(int[][] section, int XL, int YL) {
        for (int x = 0; x < XL; x++) {
            bfsHelper(section, x, 0, XL, YL, AIR | STONE);
            bfsHelper(section, x, YL - 1, XL, YL, AIR | STONE);
        }

        for (int y = 0; y< YL; y++) {
            bfsHelper(section, 0, y, XL, YL, AIR | STONE);
            bfsHelper(section, XL - 1, y, XL, YL, AIR | STONE);
        }
        int totalArea = 0;
        for (int x = 0; x < XL; x++) {
            for (int y = 0; y < YL; y++) {
                if (section[y][x] == EMPTY) {
                    totalArea += 1;
                }
            }
        }
        return totalArea;
    }

    public void bfsHelper(int[][] matMark, int x, int y, int XL, int YL, int markVal) {
        if ((matMark[y][x] & markVal) == 0) {
            matMark[y][x] |= AIR; // mark first node
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
                        matMark[adjy][adjx] |= AIR;
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

public class Leet407x_t2 {
    public static void main(String[] args) {
        int[][] heightmap = {
                {1, 0, 0, 0, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 1},
        };
        heightmap = new int[][] {
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        };
        heightmap = new int[][] {
                {1,3,3,1,3,2},
                {3,2,1,3,2,3},
                {3,3,3,2,3,1}};;
        Leet407x2 leet = new Leet407x2();
        System.out.println(leet.trapRainWater(heightmap));
        //int[][] section = leet.getSection(heightmap, 1, 9, 9);
        //ArrayUtil.showArr2D(section);
        //System.out.println(leet.getArea(section, 9, 9));
        //ArrayUtil.showArr2D(section);
    }
}
