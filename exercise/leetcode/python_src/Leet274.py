class Solution(object):
    def hIndex(self, citations):
        """
        :type citations: List[int]
        :rtype: int
        """
        citations.sort(reverse=True)
        h = 0
        for i in range(len(citations)):
            if citations[i] >= i + 1:
                h += 1
            else:
                break
        return h

if __name__ == "__main__":
    s = Solution()
    c =  [15, 11]
    print(s.hIndex(c))
