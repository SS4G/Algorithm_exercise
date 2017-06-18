## 593. Valid Square My SubmissionsBack To Contest

Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:

```
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
```

#### Note:

- All the input integers are in the range [-10000, 10000].
- A valid square has four equal sides with positive length and four equal angles (90-degree angles).
- Input points have no order.
#### tips
判断一个正方形 就要判断 4个点互联的六条边中 有四条长度相同 较短的 长度为a  另外两条长度相同较长的 长度为b
且满足 a = b^0.5 

#### mycode
```Python
import operator
class Solution(object):
    def validSquare(self, p1, p2, p3, p4):
        """
        :type p1: List[int]
        :type p2: List[int]
        :type p3: List[int]
        :type p4: List[int]
        :rtype: bool
        """
        lines = []
        lines.append((self.getDistance(p1, p2), 1, 2))  # distance st end
        lines.append((self.getDistance(p1, p3), 1, 3))  # distance st end
        lines.append((self.getDistance(p1, p4), 1, 4))  # distance st end
        lines.append((self.getDistance(p2, p3), 2, 3))  # distance st end
        lines.append((self.getDistance(p2, p4), 2, 4))  # distance st end
        lines.append((self.getDistance(p3, p4), 3, 4))  # distance st end

        lines.sort(key=operator.itemgetter(0))

        if lines[0][0] <= 0:
            return False

        for i in range(3):
            if lines[i][0] != lines[i + 1][0]:
                return False
        for i in range(4, 5):
            if lines[i][0] != lines[i + 1][0]:
                return False
        if lines[0][0]*2 != lines[4][0]:
            return False

        return True

    def getDistance(self, p1, p2):
        return (p1[0]-p2[0])**2 + (p1[1]-p2[1])**2


if __name__ == "__main__":
    s = Solution()
    p1 = [0, 0]
    p2 = [1, 1]
    p3 = [1, 0]
    p4 = [0, 1]
    p1 = [0, 0]
    p2 = [1, 1]
    p3 = [1, -1]
    p4 = [0, 2**0.5]
    print(s.validSquare(p1, p2, p3, p4))
```
