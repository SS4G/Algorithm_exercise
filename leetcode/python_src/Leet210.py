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


if __name__ == "__main__":
    s = Solution()
    lists = [[0, 1], [3, 1], [1, 3], [3, 2]]
    x = s.findOrder(4, lists)
    print(x)

