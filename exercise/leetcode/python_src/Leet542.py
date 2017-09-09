class Solution(object):
    def updateMatrix(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[List[int]]
        """
        rowLength = len(matrix)
        if rowLength != 0:
            colLength = len(matrix[0])
        else:
            return []

        resmat = []
        for i in range(rowLength):
            resmat.append([-1, ]*colLength)
        for r in range(rowLength):
            for c in range(colLength):
                if matrix[r][c] == 0:
                    resmat[r][c] = 0
                else:
                    self.findNearest(matrix, r, c, resmat)
        return resmat

    def findNearest(self, matrix, r0, c0, resmat):
        if self.attachedZero(matrix, r0, c0):
            resmat[r0][c0] = 1
            return 1
        else:
            tmpRes = []
            distance = 1
            inPtr = 1
            outPtr = 0
            tmpRes.append((r0, c0, 0))
            while outPtr < inPtr:
                r = tmpRes[outPtr][0]
                c = tmpRes[outPtr][1]
                distance = tmpRes[outPtr][2]+1
                if r + 1 < len(matrix) and ((r+1, c, distance) not in tmpRes):
                    if self.attachedZero(matrix, r+1, c):
                        resmat[r0][c0] = distance+1
                        break
                    else:
                        tmpRes.append((r+1, c, distance))
                        inPtr += 1

                if c + 1 < len(matrix[0]) and ((r, c+1, distance) not in tmpRes):
                    if self.attachedZero(matrix, r, c+1):
                        resmat[r0][c0] = distance+1
                        break
                    else:
                        tmpRes.append((r, c+1, distance))
                        inPtr += 1

                if r - 1 >= 0 and ((r-1, c, distance) not in tmpRes):
                    if self.attachedZero(matrix, r-1, c):
                        resmat[r0][c0] = distance+1
                        break
                    else:
                        tmpRes.append((r-1, c, distance))
                        inPtr += 1

                if c - 1 >= 0 and ((r, c-1, distance) not in tmpRes):
                    if self.attachedZero(matrix, r, c-1):
                        resmat[r0][c0] = distance+1
                        break
                    else:
                        tmpRes.append((r, c-1, distance))
                        inPtr += 1
                outPtr += 1
            return resmat[r0][c0]

    def attachedZero(self, matrix, r, c):
        if r+1 < len(matrix) and matrix[r+1][c] == 0:
            return True

        if r-1 >= 0 and matrix[r-1][c] == 0:
            return True

        if c+1 < len(matrix[0]) and matrix[r][c+1] == 0:
            return True

        if c-1 >= 0 and matrix[r][c-1] == 0:
            return True
        return False

if __name__ == "__main__":
    s = Solution()
    mat0 = [[0, 0, 0, ],
            [0, 1, 0, ],
            [0, 0, 0, ]]
    mat1 = [[0, 0, 0, ],
            [0, 1, 0, ],
            [1, 1, 1, ]]
    mat2 = [[0, 0, 0, 0],
            [0, 1, 1, 0],
            [0, 1, 1, 0],
            [1, 0, 0, 0],
            [1, 1, 1, 0],
            [0, 1, 1, 1],
            [0, 1, 1, 1],
            [0, 1, 1, 1],
            ]
    mat3 = [
 [1, 1, 0, 0, 1, 0, 0, 1, 1, 0],
 [1, 0, 0, 1, 0, 1, 1, 1, 1, 1],
 [1, 1, 1, 0, 0, 1, 1, 1, 1, 0],
 [0, 1, 1, 1, 0, 1, 1, 1, 1, 1],
 [0, 0, 1, 1, 1, 1, 1, 1, 1, 0],
 [1, 1, 1, 1, 1, 1, 0, 1, 1, 1],
 [0, 1, 1, 1, 1, 1, 1, 0, 0, 1],
 [1, 1, 1, 1, 1, 0, 0, 1, 1, 1],
 [0, 1, 0, 1, 1, 0, 1, 1, 1, 1],
 [1, 1, 1, 0, 1, 0, 1, 1, 1, 1]]

    # print(s.attachedZero(mat1, 1, 1))
    # x = mat3.copy()
    # print(s.findNearest(mat3, 9, 9, x))
    #for line in s.updateMatrix(mat3):
        #print(line)
    print(s.updateMatrix(mat3))