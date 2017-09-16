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