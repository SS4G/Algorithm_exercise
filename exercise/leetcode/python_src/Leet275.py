class Solution(object):
    def hIndex(self, citations):
        """
        :type citations: List[int]
        :rtype: int
        """
        citations.reverse()
        lo = 0
        hi = len(citations) - 1
        while lo <= hi:
            mid = (lo + hi) >> 1
            if citations[mid] >= mid + 1:
                lo = mid + 1
            else:
                hi = mid - 1
        return hi + 1

if __name__ == "__main__":
    s = Solution()
    c = []
    print(s.hIndex(c))


