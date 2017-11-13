class NumMatrix(object):
    def __init__(self, matrix):
        """
        :type matrix: List[List[int]]
        """
        self.mat = matrix
        self.rL = len(matrix)
        if self.rL > 0:
            self.cL = len(matrix[0])
        else:
            self.cL = -1
        self.isEmpty = False
        if self.cL > 0 and self.rL > 0:
            self.resRecord = []
            for i in range(self.rL):
                self.resRecord.append([None, ]*self.cL)

            for i in range(self.rL):
                for j in range(self.cL):
                    if j != 0:
                        # 此处是瓶颈所在 需要优化
                        self.resRecord[i][j] = self.resRecord[i][j-1] + sum([self.mat[k][j] for k in range(i + 1)])
                    else:
                        if i == 0:
                            self.resRecord[0][0] = self.mat[0][0]
                        else:
                            self.resRecord[i][0] = self.resRecord[i - 1][0] + self.mat[i][0]
        else:
            self.isEmpty = True

    def sumRegion(self, row1, col1, row2, col2):
        """
        :type row1: int
        :type col1: int
        :type row2: int
        :type col2: int
        :rtype: int
        """
        if self.isEmpty:
            return 0
        else:
            res = self.getSumRecord(row2, col2) - self.getSumRecord(row2, col1 - 1) - self.getSumRecord(row1 - 1, col2)\
                + self.getSumRecord(row1 - 1, col1 - 1)
        return res

    def getSumRecord(self, r, c):
        if r < 0 or c < 0:
            return 0
        return self.resRecord[r][c]

if __name__ == "__main__":

    matrix = [
      [3, 0, 1, 4, 2],
      [5, 6, 3, 2, 1],
      [1, 2, 0, 1, 5],
      [4, 1, 0, 1, 7],
      [1, 0, 3, 0, 5]
    ]
    s = NumMatrix(matrix)
    print(s.sumRegion(2, 1, 4, 3))
    print(s.sumRegion(1, 1, 2, 2))
    print(s.sumRegion(1, 2, 2, 4))
    #print(s.sumRegion(2, 1, 4, 3))
    print(s.resRecord)
    print(s.sumRegion(0, 0, 1, 1) == 14)
    print(s.sumRegion(0, 0, 0, 0) == 3)
    print(s.sumRegion(0, 0, 0, 1) == 3)
    print(s.sumRegion(0, 0, 1, 0) == 8)