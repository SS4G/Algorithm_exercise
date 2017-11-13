## 657. Judge Route Circle

Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:

```
Input: "UD"
Output: true
```

Example 2:

```
Input: "LL"
Output: false
```
#### tips
注意只需要判断最终的结果是不是原点 中间出现原点不能算是假 注意空输入 也是真

#### mycode

```
class Solution:
    def judgeCircle(self, moves):
        """
        :type moves: str
        :rtype: bool
        """
        curPos = (0, 0)
        for ins in moves:
            curPos = self.getNext(curPos[0], curPos[1], ins)
            if curPos == (0, 0):
                return True

        return False

    def getNext(self, x, y, instruction):
        if instruction == "U":
            return (x, y - 1)
        elif instruction == "D":
            return (x, y + 1)
        elif instruction == "L":
            return (x - 1, y)
        elif instruction == "R":
            return (x + 1, y)

if __name__ == "__main__":
    s = Solution()
    assert s.judgeCircle("UD")
    assert not s.judgeCircle("LL")
```
