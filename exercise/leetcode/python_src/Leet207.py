class Solution(object):
    def canFinish(self, numCourses, prerequisites):
        """
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: bool
        """
        # preprocess
        prerequisites0 = prerequisites
        prerequisites = [[] for i in range(numCourses)]
        for i in prerequisites0:
            prerequisites[i[0]].append(i[1])

        # 邻接表表示的有向图 不是邻接矩阵
        markList = [0xffffffff, ]*numCourses
        for i in range(numCourses):
            searchId = i+10
            if not self.DFSHelper(i, prerequisites, markList, searchId, set([])):
                return False
        return True

    def DFSHelper(self, thisCourse, prerequisites, markList, searchId, pathTrack):
        """
        :param thisCourse:
        :param prerequisites:
        :param markList:
        :param searchId:
        :param pathTrack: set
        :return:
        """
        if markList[thisCourse] == searchId:  # already serched
            if thisCourse not in pathTrack:  # find direct cycle
                return True
            else:
                return False
        elif markList[thisCourse] < searchId:  # 之前搜做过没有问题的可以不用继续搜索 减少时间
            return True
        else:
            markList[thisCourse] = searchId  # if course searched mark him
            res = True
            pathTrack.add(thisCourse)
            for preCourse in prerequisites[thisCourse]:
                res = res and self.DFSHelper(preCourse, prerequisites, markList, searchId, pathTrack)
            pathTrack.remove(thisCourse)
            return res

if __name__ == "__main__":
    s = Solution()
    # testcase is disabled
    # assert s.canFinish(2, [[1, 0], [0, 1]]) is False, "wrong Answer"
    # assert s.canFinish(5, [[1, ], [2, ], [3, ], [4, ], [2, ]]) is False  # F
    # assert s.canFinish(5, [[1, ], [2, 3, ], [4, ], [4, ], []])  # T
    # assert s.canFinish(5, [[1, 2, 4], [3, ], [4, ], [], []])  # T
    # assert s.canFinish(7, [[1, 2, ], [3, 4], [5, 6], [], [], [], []])  # T
    # assert s.canFinish(7, [[1, 2, ], [], [], [0, 4], [5, 6], [], []])  # T
