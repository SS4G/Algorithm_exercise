## 133. Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:


```
。     1
      / \
     /   \
    0 --- 2
         / \
         \_/
```
#### tips
这个题目使用的是DFS
在对原图进行DFS的同时 创建新图
 

每个节点有三种状态
1.未创建
2.创建 但其neighbors为空
3.创建并且已经填充了它的neighbors
使用一个由label索引的字典来保存新的图的节点对象
使用一个set 来保存 创建并且neighbors 已经填充的点

生成新图的步骤如下：  
1.访问原图的当前的节点
2.将原图的当前节点的neighbors 如果没有创建 就创建新的节点对象 添加到新图对应节点的neighbors中这些节点现在是空的
3. 使用DFS 去遍历那些 创建但是neighbors为空的节点 去填充他们的neighbors   

重点是要搞清楚他们的状态 哪些点需要去DFS 那些点属于已经标记过的不用去DFS 可以想象着你照着一幅图去画另一幅图的过程 先画出一个新点， 然后照着旧图 画出新点所对应的所有边
如果所有边所指向的点都已经完成 这个点就算完成了


#### mycode

```
# Definition for a undirected graph node
class UndirectedGraphNode:
    def __init__(self, x):
        self.label = x
        self.neighbors = []

#class GraphUtils:
#    def showGraph(self, node):



class Solution:
    # @param node, a undirected graph node
    # @return a undirected graph node
    def cloneGraph(self, node):
        nodeDict = {}  # label->nodeReferance
        n0 = UndirectedGraphNode(node.label)
        nodeDict[n0.label] = n0
        compeleteSet = set([])
        self.dfsHelper(node, n0, nodeDict, compeleteSet)
        return n0

    def dfsHelper(self, node, nodeCopy, nodeDict, compeleteSet):
        if node.label in compeleteSet:
            return
        for n in node.neighbors:  # 说明当前节点是个空点 需要构造他的邻接表
            if n.label not in nodeDict:  # add new node
                nodeCopy.neighbors.append(UndirectedGraphNode(n.label))
                nodeDict[n.label] = nodeCopy.neighbors[-1]
            else:
                nodeCopy.neighbors.append(nodeDict[n.label])
        compeleteSet.add(node.label)
        for i in range(len(node.neighbors)):
            self.dfsHelper(node.neighbors[i], nodeCopy.neighbors[i], nodeDict, compeleteSet)

if __name__ == "__main__":
    s = Solution()
    nodes = [
        UndirectedGraphNode(0),
        UndirectedGraphNode(1),
        UndirectedGraphNode(2),
        UndirectedGraphNode(3),
        UndirectedGraphNode(4),
    ]
    nodes[0].neighbors = [nodes[1], nodes[2]]
    nodes[1].neighbors = [nodes[0], nodes[2]]
    nodes[2].neighbors = [nodes[0], nodes[2], nodes[1]]
    nodes[3].neighbors = [nodes[0], nodes[4]]
    nodes[4].neighbors = [nodes[3], nodes[0]]
    cloned = s.cloneGraph(nodes[0])
    print([i.label for i in cloned.neighbors])
```
