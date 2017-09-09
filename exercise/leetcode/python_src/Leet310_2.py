import operator
class Solution(object):
    def findMinHeightTrees(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[int]
        """
        if n == 1:
            return [0]
        graph = self.buildGraph(n, edges)
        res = self.stripLeaf(graph)
        print(res)
        return res

    def buildGraph(self, n, edges):
        graph = []
        for i in range(n):
            graph.append([])
        for edge in edges:
            graph[edge[0]].append(edge[1])
            graph[edge[1]].append(edge[0])
        return graph

    def stripLeaf(self, graph):
        cutted = [False, ]*len(graph)
        adjNum = [len(n) for n in graph]
        rest = len(graph)
        while rest > 2:
            rest = 0
            tobeCutted = []
            for x in range(len(graph)):
                if adjNum[x] <= 1 and not cutted[x]:
                    tobeCutted.append(x)

            for i in tobeCutted:
                cutted[i] = True
                adjNum[i] -= 1
                for k in graph[i]:
                    adjNum[k] -= 1
            for i in cutted:
                if not i:
                    rest += 1

        return list(filter(lambda x: not cutted[x], range(len(graph))))

if __name__ == "__main__":
    s = Solution()
    g = [[1, 6], [6, 11], [2, 10], [10, 11], [5, 9], [9, 11], [4, 8], [8, 11], [3, 7], [7, 11], [0, 12], [12, 11]]
    print(s.findMinHeightTrees(13, g))
