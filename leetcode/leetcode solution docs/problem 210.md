## 210. Course Schedule II

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:


```
2, [[1,0]]
```

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]


```
4, [[1,0],[2,0],[3,1],[3,2]]
```

There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
#### tips
这个题目是典型的拓扑排序  
第一步用dfs检测有向环  
第二步使用dfs 生成逆后序排列   
需要注意对图的预处理   
使用邻接表生成图   

#### mycode
```
class Solution(object):
    def findOrder(self, numCourses, prerequisites):
        """
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: List[int]
        """
        prerequisites = self.reverseGraph(self.genGraph(prerequisites, numCourses), numCourses)
        print(prerequisites)

        if self.hasDirectLoopCheck(prerequisites):
            print("loop pfound")
            return []
        else:
            output = []
            markList1 = [False, ] * numCourses
            for i in range(numCourses):
                if markList1[i] is False:
                    self.dfsHelper1(prerequisites, i, markList1, output)
            output.reverse()
            return output

    def genGraph(self, pairs, nodeNum):
        graph = []
        for i in range(nodeNum):
            graph.append([])
        for j in pairs:
            graph[j[0]].append(j[1])
        return graph



    def hasDirectLoopCheck(self, prerequisites):
        markList = [False, ]*len(prerequisites)
        pathSet = set([])
        res = False
        for i in range(len(markList)):
            if markList[i] is False:
                res = res or self.dfsHelper0(prerequisites, i, markList, pathSet)
        return res

    def dfsHelper0(self, graph, cur, markList, pathSet):
        # 用来检测图中是否存在有向环
        adj = graph[cur]
        if markList[cur] is True:
            return True if cur in pathSet else False
        else:
            markList[cur] = True
            pathSet.add(cur)
            res = False
            for n in adj:
                res = res or self.dfsHelper0(graph, n, markList, pathSet)
            pathSet.remove(cur)
            return res

    def dfsHelper1(self, graph, cur, markList, output):
        # 用来进行你后续序列的生成
        if markList[cur] is True:
            return
        else:
            markList[cur] = True
            adj = graph[cur]
            for n in adj:
                self.dfsHelper1(graph, n, markList, output)
            output.append(cur)
            return

    def reverseGraph(self, graphSrc, nodeNum):
        newGraph = []
        for i in range(nodeNum):
            newGraph.append([])
        for i in range(nodeNum):
            for dest in graphSrc[i]:
                newGraph[dest].append(i)
        return newGraph
```
