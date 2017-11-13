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