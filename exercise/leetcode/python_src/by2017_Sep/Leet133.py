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

