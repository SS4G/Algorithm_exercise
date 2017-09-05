class Solution(object):
    def maximalSquare(self, matrix):
        """
        :type matrix: List[List[str]]
        :rtype: int
        """
        rL = len(matrix)
        if rL == 0:
            return 0
        cL = len(matrix[0])
        newMat = []
        for i in range(rL):
            newMat.append([None, ]*cL)
        for r in range(rL):
            for c in range(cL):
                if matrix[r][c] == '1':
                    newMat[r][c] = 1
                else:
                    newMat[r][c] = 0
        matrix = newMat
        verMat, horMat = self.markAdjInfoForMat(matrix, rL, cL)
        maxSquareRecord = []
        for i in range(rL):
            maxSquareRecord.append([0, ]*cL)
        maxLen = 0
        for r0 in range(rL):
            for c0 in range(cL):
                if matrix[r0][c0] == 1:
                    if r0 == 0 or c0 == 0:
                        maxSquareRecord[r0][c0] = 1
                    else:
                        maxSquareRecord[r0][c0] =\
                            min([maxSquareRecord[r0 - 1][c0 - 1], verMat[r0][c0], horMat[r0][c0]]) + 1
                    maxLen = max(maxLen, maxSquareRecord[r0][c0])
        self.showMat("maxSquare", maxSquareRecord)
        return maxLen**2

    def markAdjInfoForMat(self, matrix, rL, cL):
        verticalInfoMat = []
        horizontalInfoMat = []
        for i in range(rL):
            verticalInfoMat.append([-1, ]*cL)
            horizontalInfoMat.append([-1, ]*cL)
        for c0 in range(cL):
            for rx in range(rL):
                if matrix[rx][c0] == 1:
                    if rx == 0:
                        verticalInfoMat[rx][c0] = 0
                    else:
                        if verticalInfoMat[rx - 1][c0] == -1:
                            verticalInfoMat[rx][c0] = 0
                        else:
                            verticalInfoMat[rx][c0] = verticalInfoMat[rx - 1][c0] + 1

        for r0 in range(rL):
            for cx in range(cL):
                if matrix[r0][cx] == 1:
                    if cx == 0:
                        horizontalInfoMat[r0][cx] = 0
                    else:
                        if horizontalInfoMat[r0][cx - 1] == -1:
                            horizontalInfoMat[r0][cx] = 0
                        else:
                            horizontalInfoMat[r0][cx] = horizontalInfoMat[r0][cx - 1] + 1
        return verticalInfoMat, horizontalInfoMat

    def showMat(self, info, mat):
        print("---------", info)
        for l in mat:
            print(l)
        print("_________")
if __name__ == "__main__":
    s = Solution()
    mat = [[1, 0, 1, 0, 0],
           [1, 0, 1, 1, 1],
           [1, 1, 1, 1, 1],
           [1, 0, 0, 1, 0],
           ]
    print(s.maximalSquare(mat))