class Solution(object):
    def numTrees(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n == 0:
            return 1
        records = [-1, ] * (n + 1)
        if n > 0:
            records[0] = 1
            records[1] = 1
        if n >= 2:
            records[2] = 2

        if n >= 3:
            records[3] = 5
        return self.calcRecursive(n, records)

    def calcRecursive(self, n, records):
        if records[n] != -1:
            return records[n]
        else:
            if n & 0x01 == 0:  # even
                sum0 = sum([self.calcRecursive(i, records) * self.calcRecursive(n - 1 - i, records) for i in range(n >> 1)]) << 1
                records[n] = sum0
            else:
                sum0 = sum([self.calcRecursive(i, records) * self.calcRecursive(n - 1 - i, records) for i in range(n >> 1)]) << 1
                sum0 += self.calcRecursive(n >> 1, records) * self.calcRecursive(n >> 1, records)
                records[n] = sum0
            return sum0

if __name__ == "__main__":
    s = Solution()
    assert s.numTrees(0) == 1, "WA"
    assert s.numTrees(1) == 1, "WA"
    assert s.numTrees(2) == 2, "WA"
    assert s.numTrees(3) == 5, "WA"
    assert s.numTrees(4) == 14, "WA"
    assert s.numTrees(5) == 42, "WA"
    assert s.numTrees(6) == 132, "WA"