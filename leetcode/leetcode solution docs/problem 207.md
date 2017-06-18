## 207. Course Schedule Add to List

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:


```
2, [[1,0]]
```

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.


```
2, [[1,0],[0,1]]
```

There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

#### tips
其实就是去查找里面有没有有向环，使用DFS 结合路径中是否有访问过得点 来检测是否有有向环 
可以取巧的地方时 每次从一个没有开始遍历的点开始， 就给所有遍历过的点 加一个 searchID 表示是第几次搜索过的点 在之后 如果遇到searcheID小于当前searchID的点就说明 这个ID较小的点已经被验证过不在有向环上面， 可以不继续搜索直接返回True 可以减少很多重复的工作

#### mycode
```Python
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
```
