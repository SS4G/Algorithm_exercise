class Solution(object):
    def leastBricks(self, wall):
        """
        :type wall: List[List[int]]
        :rtype: int
        """
        if len(wall) == 0:
            return 0
        elif len(wall) == 1 and len(wall[0]) == 1:
            return 1

        offsetDict = {}
        offset = 0
        for row in wall:
            offset = 0
            for brick in row:
                offset += brick
                if offset not in offsetDict:
                    offsetDict[offset] = 1
                else:
                    offsetDict[offset] += 1
            end = offset
        keyList = [None, ]*len(offsetDict)
        i = 0
        for key in offsetDict:
            if key != end:
                keyList[i] = offsetDict[key]
                i += 1
        keyList[-1] = 0
        return len(wall)-max(keyList)
if __name__ == "__main__":
    wall = [[1, 2, 2, 1],
     [3, 1, 2],
     [1, 3, 2],
     [2, 4],
     [3, 1, 2],
     [1, 3, 1, 1]]
    wall2 = [[7, ], [7, ], [7, ]]
    s = Solution()
    print(s.leastBricks(wall))
    print(s.leastBricks(wall2))