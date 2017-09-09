## 200. Number of Islands 
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:


```
11110
11010
11000
00000
```

Answer: 1

Example 2:


```
11000
11000
00100
00011
```

Answer: 3
#### tips
使用标准的深度搜索即可
需要注意的是如何把一个问题转化为一个联通的图 来进一步转化成为深度搜索问题
#### mycode
```Java
//DFS beat 40%
import java.util.*;
class Leet200 {
    private final char ISLAND = '1';
    private final int INITMARK = 0;
    public int numIslands(char[][] grid) {
        if (grid.length <= 0)
            return 0;
        int gridX = grid[0].length;
        int gridY = grid.length;
        int[][] matMark = new int[gridY][gridX];
        for (int tmpx = 0; tmpx < gridX; tmpx++)
            for (int tmpy = 0; tmpy < gridY; tmpy++)
                matMark[tmpy][tmpx] = INITMARK;

        int markVal = 1;
        for (int tmpx = 0; tmpx < gridX; tmpx++)
            for (int tmpy = 0; tmpy < gridY; tmpy++) {
                if (grid[tmpy][tmpx] == ISLAND  && matMark[tmpy][tmpx] == INITMARK) {
                    dfsMark(matMark, grid, tmpx, tmpy, markVal);
                    markVal ++;
                }
            }

        return markVal-1;
    }
    private void dfsMark(int[][] mark, char[][] grid, int x, int y, int markVal) {
        if (mark[y][x] == INITMARK) {
            mark[y][x] = markVal;
            if (x+1 < grid[0].length && grid[y][x+1] == ISLAND)
                dfsMark(mark, grid, x+1, y, markVal);
            if (y+1 < grid.length && grid[y+1][x] == ISLAND)
                dfsMark(mark, grid, x, y+1, markVal);
            if (x-1 >= 0 && grid[y][x-1] == ISLAND)
                dfsMark(mark, grid, x-1, y, markVal);
            if (y-1 >= 0 && grid[y-1][x] == ISLAND)
                dfsMark(mark, grid, x, y-1, markVal);
        }
    }
}

//testbench
public class Leet200_t {
    public static void main(String[] args) {
        Leet200 s = new Leet200();
        char[][] sea0 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'},
        };
        char[][] sea1 =  {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
        };

        System.out.println(s.numIslands(sea1));
        System.out.println(s.numIslands(sea0));
    }
}
```
