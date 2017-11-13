class Solution(object):
    def minimumTotal(self, triangle):
        """
        :type triangle: List[List[int]]
        :rtype: int
        """
        rL = len(triangle)
        lastRowRecord = [0, ]*rL
        row = rL - 1
        while row >= 0:
            if row == rL - 1:
                lastRowRecord = triangle[-1][:]
            else:
                oldLastRowRecord = lastRowRecord[:]
                for j in range(row + 1):
                    lastRowRecord[j] = triangle[row][j] + min(oldLastRowRecord[j], oldLastRowRecord[j+1])
            row -= 1
        return lastRowRecord[0]

if __name__ == "__main__":
    s = Solution()
    tri = [
     [2],
    [3, 4],
   [6, 5, 7],
  [4, 1, 8, 3]
]
    print(s.minimumTotal(tri))
