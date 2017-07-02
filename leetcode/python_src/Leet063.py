class Solution(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """
        WAY = 0
        STONE = 1
        mat = obstacleGrid
        rL = len(mat)
        if rL == 0:
            return 0
        cL = len(mat[0])
        matRec = [[0, ] * cL for r in range(rL)]

        for c in range(cL):
            if mat[0][c] == WAY:
                if c == 0:
                    matRec[0][0] = 1
                else:
                    matRec[0][c] = matRec[0][c - 1]

        for r in range(rL):
            if mat[r][0] == WAY:
                if r == 0:
                    pass  # has checked before
                else:
                    matRec[r][0] = matRec[r - 1][0]

        for r in range(1, rL):
            for c in range(1, cL):
                if mat[r][c] == WAY:
                    matRec[r][c] = matRec[r - 1][c] + matRec[r][c - 1]
        return matRec[rL - 1][cL - 1]

if __name__ == "__main__":
    s = Solution()
    mat = [
      [0, 0, 0],
      [0, 1, 0],
      [0, 0, 0]
    ]
    print(s.uniquePathsWithObstacles(mat))
