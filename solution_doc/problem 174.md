The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.


-2 | -3 | 3
---|----|---
-5 | -10| 1
10 | 30 | -5



Notes:

- The knight's health has no upper bound.
- Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
- 


#### tips
这个题目的dp不能从左上到右下 因为 这样不知道当前的血量是不是多余的 应该从右下到左上倒着来考虑

如上图 首先假设 王子子血量为0的情况下不会GG
那么 最右下角需要王子初始至少有5的生命值  
假设这个角的坐标是(2, 2)
那么 dp[x, y] x 为行 y 为列  
dp[2, 1] = 0 //这里是0 是因为 王子一下来就会吃到生命值 所以什么都不用带   
dp[1, 2] = 4 这个需要王子一开始就带有至少4的生命值 否则会GG

所以 dp方程是

```
int tmp = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                    dp[i][j] = tmp > 0 ? tmp : 0;
```

即选取下侧和右侧的最小者 他们中至少为0 加上当前格子的负值 如果当前格子大于0 那么这个dp值最后可能是负的 负的表示王子即使一开始生命值是负的也可以 但是题目不允许 那么久让这个格子至少为0 就说明王子什么也不用带就可以

但是最后需要注意 因为 生命值不能为0
所以 最后要在 dp[0][0] 的基础上加一 这样保证王子在路径上始终生命值为正的最低限度

#### mycode


```
class Leet174x {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for (int i = dungeon.length - 1; i >= 0; i--) {
            for (int j = dungeon[0].length - 1; j >= 0; j--) {
                if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
                    dp[i][j] = dungeon[i][j] >= 0 ? 0 : -dungeon[i][j];
                } else if (i == dungeon.length - 1) {
                    //dp[i][j] = dp[i][j + 1] + dungeon[i][j] >= 0 ? 0 : -dungeon[i][j];
                    int tmp = dp[i][j + 1] - dungeon[i][j];
                    dp[i][j] = tmp > 0 ? tmp : 0;
                } else if (j == dungeon[0].length - 1) {
                    int tmp = dp[i + 1][j] - dungeon[i][j];
                    dp[i][j] = tmp > 0 ? tmp : 0;
                } else {
                    int tmp = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                    dp[i][j] = tmp > 0 ? tmp : 0;
                }
            }
        }
        //ArrayUtil.showArr2D(dp);
        return dp[0][0] + 1;
    }
}
```






