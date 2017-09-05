class Solution(object):
    def __init__(self):
        self.TO_RIGHT = 0
        self.TO_DOWN = 1
        self.TO_LEFT = 2
        self.TO_UP = 3
        self.nextDirection = [self.TO_DOWN, self.TO_LEFT, self.TO_UP, self.TO_RIGHT]  # rotated array

    def generateMatrix(self, n):
        """
        :type n: int
        :rtype: List[List[int]]
        """

        mark = []
        rL = n
        cL = n
        a = [False, ] * cL
        b = [0, ]*cL
        matrix = []
        for i in range(rL):
            mark.append(a[:])
            matrix.append(b[:])
        i = 0
        curDirection = self.TO_RIGHT
        r = 0
        c = 0

        while i < rL*cL:
            matrix[r][c] = i + 1
            mark[r][c] = True
            r, c, curDirection = self.getNext(r, c, curDirection, rL, cL, mark)
            i += 1
        return matrix

    def getNext(self, r, c, curDir, rL, cL, mark):
        if curDir == self.TO_RIGHT:
            if c + 1 >= cL or mark[r][c + 1]:
                curDir = self.nextDirection[self.TO_RIGHT]
                return r + 1, c, curDir
            else:
                return r, c + 1, curDir
        elif curDir == self.TO_DOWN:
            if r + 1 >= rL or mark[r + 1][c]:
                curDir = self.nextDirection[self.TO_DOWN]
                return r, c - 1, curDir
            else:
                return r + 1, c, curDir
        elif curDir == self.TO_LEFT:
            if c - 1 < 0 or mark[r][c - 1]:
                curDir = self.nextDirection[self.TO_LEFT]
                return r - 1, c, curDir
            else:
                return r, c - 1, curDir
        elif curDir == self.TO_UP:
            if r - 1 < 0 or mark[r - 1][c]:
                curDir = self.nextDirection[self.TO_UP]
                return r, c + 1, curDir
            else:
                return r - 1, c, curDir
        else:
            assert False, "invalid args"

if __name__ == "__main__":
    s = Solution()
    r = s.generateMatrix(1)
    for l in r:
        print(l)