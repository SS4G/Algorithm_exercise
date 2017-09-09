## 554. Brick Wall
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Example:

```
Input: 
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
```

Explanation: 
![img](https://leetcode.com/static/images/problemset/brick_wall.png)
Note:
The width sum of bricks in different rows are the same and won't exceed INT_MAX.
The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
#### tips
use map to slove this 
#### mycode

```Python
class Solution(object):
    def leastBricks(self, wall):
        """
        :type wall: List[List[int]]
        :rtype: int
        """
        if len(wall) == 0:
            return 0
        elif len(wall) == 1 and len(wall[0]) == 1:
            return 1

        offsetDict = {}
        offset = 0
        for row in wall:
            offset = 0
            for brick in row:
                offset += brick
                if offset not in offsetDict:
                    offsetDict[offset] = 1
                else:
                    offsetDict[offset] += 1
            end = offset
        keyList = [None, ]*len(offsetDict)
        i = 0
        for key in offsetDict:
            if key != end:
                keyList[i] = offsetDict[key]
                i += 1
        keyList[-1] = 0
        return len(wall)-max(keyList)
if __name__ == "__main__":
    wall = [[1, 2, 2, 1],
     [3, 1, 2],
     [1, 3, 2],
     [2, 4],
     [3, 1, 2],
     [1, 3, 1, 1]]
    wall2 = [[7, ], [7, ], [7, ]]
    s = Solution()
    print(s.leastBricks(wall))
    print(s.leastBricks(wall2))
```
