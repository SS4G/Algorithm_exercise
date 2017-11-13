package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/14.
 */
import java.util.*;
class Leet200x {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int[][] mark = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0)
                    mark[i][j] = -1;
            }
        }
        int val = 0;
        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[y][x] != '0' && mark[y][x] == -1) {
                    val++;
                    dfsHelper(mark, grid, x, y, val);
                }
            }
        }
        return val;
    }

    public void dfsHelper(int[][] mark, char[][] grid, int x, int y, int val) {
        mark[y][x] = val;
        List<List<Integer>> adjs = getAdj(mark[0].length, mark.length, x, y);
        for (List<Integer> adjNode : adjs) {
            int xPos = adjNode.get(0);
            int yPos = adjNode.get(1);
            if (grid[yPos][xPos] != '0' && mark[yPos][xPos] == -1) {
                dfsHelper(mark, grid, xPos, yPos, val);
            }
        }
    }

    List<List<Integer>> getAdj(int xL, int yL, int x, int y) {
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
        List<List<Integer>> adjs = new ArrayList<>();
        for (int x0 : xs) {
            List<Integer> xx = new ArrayList<>();
            xx.add(x0);
            xx.add(y);
            adjs.add(xx);
        }

        for (int y0 : ys) {
            List<Integer> yy = new ArrayList<>();
            yy.add(x);
            yy.add(y0);
            adjs.add(yy);
        }
        return adjs;
    }
}

public class Leet200x_t {
    public static void main(String[] args) {
        Leet200x leet = new Leet200x();
        char[][] maps = {
                {1,1,1,1,0},
                {1,1,0,1,0},
                {1,1,0,0,0},
                {0,0,0,0,0},
        };
        /*
        maps = new char[][]{
                {1,1,0,0,0,},
                {1,1,0,0,0,},
                {0,0,1,0,0,},
                {0,0,0,1,1,},
        };*/
        System.out.println(leet.numIslands(maps));
    }
}
