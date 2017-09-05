# leetcode 463
## Question 
#### Island Perimeter   

Contributors: amankaraj
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

```
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]
```
![img](https://leetcode.com/static/images/problemset/island.png)

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:

## Answer
逐个块进行判断 然后对每个块处于外部的边长进行累加 进而求出周长
##### code 
```
class Solution(object):
    def islandPerimeter(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        height=len(grid)
        width=0
        sum=0;
        if height>0:
            width=len(grid[0])
        if height>0 and width>0:
            if height==1 and width==1:
                return 4
            else:           
                for i in range(height):
                    for j in range(width):
                        up      =False
                        down    =False
                        left    =False
                        right   =False
                        if grid[i][j]:
                            if i==0 :#判断该块的某一部分是否处在边界上
                                sum+=1
                                up=True
                            if j==0:
                                sum+=1
                                left=True
                            if i==height-1:
                                sum+=1
                                down=True
                            if j==width-1:
                                sum+=1
                                right=True
                            if not up     and grid[i-1][j]==0:#和上面的边界比较是互斥的
                                sum+=1  
                            if not down   and grid[i+1][j]==0:
                                sum+=1    
                            if not left   and grid[i][j-1]==0:
                                sum+=1    
                            if not right  and grid[i][j+1]==0:
                                sum+=1
                return sum
        else:
            return 0
```
