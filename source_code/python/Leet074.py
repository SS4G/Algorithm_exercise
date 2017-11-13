class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if len(matrix) == 0:
            return False

        if matrix[0][0] > target:
            return False
        if matrix[len(matrix) - 1][len(matrix[0]) - 1] < target:
            return False
        vIndex = self.findFirstGreaterEqualVertical(matrix, target, len(matrix[0]) - 1)
        hIndex = self.findFirstGreaterEqualHorizontal(matrix, target, vIndex)
        if matrix[vIndex][hIndex] == target:
            return True
        else:
            return False

    def findFirstGreaterEqualVertical(self, mat, target, columnFixed):
        lo = 0
        hi = len(mat) - 1
        while lo <= hi:
            mid = (lo + hi) >> 1
            if mat[mid][columnFixed] == target:
                return mid
            elif mat[mid][columnFixed] > target:
                hi = mid - 1
            else:
                lo = mid + 1
        return lo

    def findFirstGreaterEqualHorizontal(self, mat, target, rowFixed):
        lo = 0
        hi = len(mat[0]) - 1
        while lo <= hi:
            mid = (lo + hi) >> 1
            if mat[rowFixed][mid] == target:
                return mid
            elif mat[rowFixed][mid] > target:
                hi = mid - 1
            else:
                lo = mid + 1
        return lo

if __name__ == "__main__":
    s = Solution()
    mat = [
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    assert s.searchMatrix(mat, 3) is True, "WA"
    assert s.searchMatrix(mat, 51) is False, "WA"
    assert s.searchMatrix(mat, -1) is False, "WA"
    assert s.searchMatrix(mat, 25) is False, "WA"
    assert s.searchMatrix(mat, 23) is True, "WA"
    assert s.searchMatrix(mat, 16) is True, "WA"