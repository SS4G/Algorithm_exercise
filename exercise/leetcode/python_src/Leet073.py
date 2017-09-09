class Solution(object):
    def setZeroes(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: void Do not return anything, modify matrix in-place instead.
        """
        rL = len(matrix)
        if rL == 0:
            return
        cL = len(matrix[0])
        rSet = set([])
        cSet = set([])
        for rx in range(rL):
            for cx in range(cL):
                if matrix[rx][cx] == 0:
                    rSet.add(rx)
                    cSet.add(cx)

        for i in rSet:
            for j in range(cL):
                matrix[i][j] = 0

        for j in cSet:
            for i in range(rL):
                matrix[i][j] = 0

        return

if __name__ == "__main__":
    s = Solution()
    mat = [
        [1, 2, 0],
        [1, 0, 0],
        [1, 2, 3]
    ]
    # [[1, 2, 0],[1, 0, 0],[1, 2, 3]]
    s.setZeroes(mat)
    for li in mat:
        print(li)