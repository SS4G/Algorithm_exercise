## 407. Trapping Rain Water II

Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:

```
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]
```

Return 4.


![img](https://leetcode.com/static/images/problemset/rainwater_empty.png)
The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.


![img](https://leetcode.com/static/images/problemset/rainwater_fill.png)
After the rain, water are trapped between the blocks. The total volume of water trapped is 4.


#### tips

[一个BFS题解](http://www.cnblogs.com/grandyang/p/5928987.html)

需要注意的是 在BFS的过程中 需要把相邻的未标记的点也放入到队列中 不管他的高度是多少 但是计算体积的时候 只计算小于海平面的 海平面 只升 不降 直观的理解就是从外围开始 让水一点点的向上涨 然后超过一个凹洞的边界后 就向凹洞里面开始漫灌 

#### mycdoe

```
class Cell implements Comparable<Cell> {
    int height;
    int x;
    int y;
    Cell(int height, int x, int y) {
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int compareTo(Cell c) {
        return height - c.height;
    }

    public String toString() {
        return "x=" + x + " y=" + y + " h=" + height + ",";
    }
}

class Leet407x3 {
    public int trapRainWater(int[][] heightMap) {
        final int YL = heightMap.length;
        if (YL == 0)
            return 0;
        final int XL = heightMap[0].length;
        if (XL == 0)
            return 0;
        int[][] mark = new int[YL][XL];
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        // add all boarder point to priority queue
        for (int x = 0; x < XL; x++) {
            pq.offer(new Cell(heightMap[0][x], x, 0));
            mark[0][x] = 1;
            pq.offer(new Cell(heightMap[YL - 1][x], x, YL - 1));
            mark[YL - 1][x] = 1;
        }

        for (int y = 0; y< YL; y++) {
            pq.offer(new Cell(heightMap[y][0], 0, y));
            mark[y][0] = 1;
            pq.offer(new Cell(heightMap[y][XL - 1], XL - 1, y));
            mark[y][XL - 1] = 1;
        }

        int volume = 0;
        int seaLevel = 0;
        while (pq.size() > 0) {
            //System.out.println();
            Cell curCell = pq.poll();
            //System.out.println(curCell);
            seaLevel = Math.max(curCell.height, seaLevel);
            List<int[]> adjs = getAdj(XL, YL, curCell.x, curCell.y);
            for (int[] adjPoint : adjs) {
                int adjx = adjPoint[0];
                int adjy = adjPoint[1];
                if (mark[adjy][adjx] == 0) { // just add unmarked
                    pq.offer(new Cell(heightMap[adjy][adjx], adjx, adjy));
                    mark[adjy][adjx]= 1;
                    if (heightMap[adjy][adjx] <= seaLevel)
                        volume += (seaLevel - heightMap[adjy][adjx]);
                }
            }
        }
        return volume;
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
```


