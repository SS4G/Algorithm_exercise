class Solution(object):
    def maxDistance(self, arrays):
        """
        :type arrays: List[List[int]]
        :rtype: int
        """
        firstMax = (-0xffffffff, -1)
        secondMax = (-0xffffffff, -1)
        firstMin = (0xffffffff, -1)
        secondMin = (0xffffffff, -1)
        flag = False
        arrid = 0
        for i in arrays:
            if len(i) > 0:
                flag = True
                maxThis = i[-1]
                minThis = i[0]
                if maxThis >= firstMax[0]:
                    secondMax = firstMax
                    firstMax = (maxThis, arrid)
                elif secondMax[0] <= maxThis < firstMax[0]:
                    secondMax = (maxThis, arrid)
                else:
                    pass
                if minThis <= firstMin[0]:
                    secondMin = firstMin
                    firstMin = (minThis, arrid)
                elif secondMin[0] >= minThis > firstMin[0]:
                    secondMin = (minThis, arrid)
                else:
                    pass
            arrid += 1

        if firstMax[1] != firstMin[1]:
            return abs(firstMax[0] - firstMin[0])
        else:
            return max(abs(firstMax[0] - secondMin[0]), abs(secondMax[0] - firstMin[0]))

if __name__ == "__main__":
    s = Solution()
    arr = [[1, 4], [0, 5]]
    print(s.maxDistance(arr))