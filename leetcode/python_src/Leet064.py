class Solution(object):
    def minPathSum(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        rL = len(grid)
        if rL == 0:
            return 0
        cL = len(grid[0])

        distaceRec = [[-1, ] * cL for i in range(rL)]
        for i in range(rL):
            if i == 0:
                distaceRec[0][0] = grid[0][0]
            else:
                distaceRec[i][0] = distaceRec[i - 1][0] + grid[i][0]

        for j in range(cL):
            if j == 0:
                pass
            else:
                distaceRec[0][j] = distaceRec[0][j - 1] + grid[0][j]

        for r in range(1, rL):
            for c in range(1, cL):
                distaceRec[r][c] = min(distaceRec[r - 1][c], distaceRec[r][c - 1]) + grid[r][c]

        return distaceRec[rL - 1][cL - 1]

if __name__ == "__main__":
    s = Solution()
    grid = [
        [1, 2, 3, 4, ],
        [4, 2, 1, 5, ],
        [2, 1, 3, 4, ],
        [1, 2, 3, 6, ],
    ]
    print(s.minPathSum(grid))

