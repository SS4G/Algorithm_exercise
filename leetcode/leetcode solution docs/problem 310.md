## 310. Minimum Height Trees

For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]


```
.
        0
        |
        1
       / \
      2   3
```

return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

```
.
     0  1  2
      \ | /
        3
        |
        4
        |
        5
```

return [3, 4]

Note:

- (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
- 
- (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


#### tips
使用从外部到内部的BFS来剥除叶子结点 这样重复多轮直到只剩下随后的两个或者一个节点
理论根据就是 高度最小树的根部出现在整个树的最长路径的中点 且只有1个或者两个高度最小树

反证法可以证明 不可能有三个及以上的最小树

#### mycode
```
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
```
