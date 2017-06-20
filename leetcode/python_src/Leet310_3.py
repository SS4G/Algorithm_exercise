class Solution(object):
    def findMinHeightTrees(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: List[int]
        """
        adj = [set() for i in range(n)]
        for edge in edges:
            adj[edge[0]].add(edge[1])
            adj[edge[1]].add(edge[0])
        leaves = [v for v in range(n) if len(adj[v]) <= 1]
        while n > 2:
            n -= len(leaves)
            new = []
            for v in leaves:
                parent = adj[v].pop()
                adj[parent].remove(v)
                if len(adj[parent]) == 1:
                    new.append(parent)
            leaves = new
        return leaves