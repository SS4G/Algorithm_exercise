import operator
class Solution(object):
    def findMinHeightTrees(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[int]
        """
        if n == 1:
            return 0
        graph = self.buildGraph(n, edges)
        res = []
        for i in range(n):
            res.append((self.calcHeight(i, graph), i))
        res.sort()
        minHeight = res[0][0]
        i = 0
        while i < len(res) and res[i][0] == minHeight:
            i += 1
        return [j[1] for j in res[:i]]

    def buildGraph(self, n, edges):
        graph = []
        for i in range(n):
            graph.append([])
        for edge in edges:
            graph[edge[0]].append(edge[1])
            graph[edge[1]].append(edge[0])
        return graph

    def calcHeight(self, node, graph):
        queue = []
        queue.append((node, 0))
        i = 0
        marked = [False, ]*len(graph)
        while i < len(queue):
            adjs = graph[queue[i][0]]
            marked[queue[i][0]] = True
            for adj in adjs:
                if not marked[adj]:
                    queue.append((adj, queue[i][1]+1))
            i += 1
        queue.sort(key=operator.itemgetter(1), reverse=True)
        return queue[0][1]

if __name__ == "__main__":
    s = Solution()
    g = [[3, 0], [3, 1], [3, 2], [3, 4], [5, 4]]
    print(s.findMinHeightTrees(6, g))
