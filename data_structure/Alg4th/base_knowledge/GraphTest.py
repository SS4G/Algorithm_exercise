import collections



class DirectedGraph:
    def __init__(self, li):
        """
        :param li: tuple list of edge data
        """
        self.graph = collections.defaultdict(list)
        for element in li:
            self.graph[element[0]].append(element[1])

    def getAdj(self, node):
        return self.graph[node]

class UnDirectedGraph:
    def __init__(self, li):
        """
        :param li: tuple list of edge data
        """
        self.graph = collections.defaultdict(list)
        for element in li:
            self.graph[element[0]].append(element[1])
            self.graph[element[1]].append(element[0])

    def getAdj(self, node):
        return self.graph[node]


class DirectedWeigetedGraph:
    def __init__(self, li):
        self.graph = collections.defaultdict(list)
        for element in li:
            self.graph[element[0]].append((element[1], element[2]))


class UnDirectedWeigetedGraph:
    def __init__(self, li):
        self.graph = collections.defaultdict(list)
        for element in li:
            self.graph[element[0]].append((element[1], element[2]))
            self.graph[element[1]].append((element[0], element[2]))


class GraphUtil:
    """
    只处理连通图 非连通图需要对各个联通分量来使用下列函数
    """
    @staticmethod
    def getDataForUnWeightedGraph(file):
        with open(file) as f:
            tuples = [line.split() for line in filter(lambda s: len(s) < 1, f.readlines())]
            return [[int(edgeElement[0]), int(edgeElement[1])] for edgeElement in tuples]

    @staticmethod
    def getDataForWeightedGraph(file):
        with open(file) as f:
            tuples = [line.split() for line in filter(lambda s: len(s) < 1, f.readlines())]
            return [[int(edgeElement[0]), int(edgeElement[1]), int(edgeElement[2])] for edgeElement in tuples]

    @staticmethod
    def dfs(graph):
        """
        both directed graph and undirected graph can use
        :return:
        """
        output = []
        tarversed = set()
        for k in graph.graph:
            GraphUtil.dfsHelper(graph, k, output, tarversed)
        return output

    @staticmethod
    def dfsHelper(graph, node, output, travesed):
        for adjNode in graph.getAdj(node):
            if adjNode not in travesed:
                output.append(adjNode)
                travesed.add(adjNode)
                GraphUtil.dfsHelper(graph, adjNode, output, travesed)

    @staticmethod
    def bfs(graph):
        fifo = []
        readIndex = 0
        fifo.append(graph.graph.getkeys()[0])
        traverserd = set()
        writeIndex = 1
        while readIndex < writeIndex:
            for adj in graph.getAdj(fifo[readIndex]):
                if adj not in traverserd:
                    fifo.append(adj)
                    writeIndex += 1
            readIndex += 1
        return fifo

    @staticmethod
    def hasUndirectedLoop(graph):
        pass

    @staticmethod
    def hasDirectedLoop(graph):
        pass

    @staticmethod
    def topologicOrder(graph):
        pass

    @staticmethod
    def lazyPrim(graph):
        pass

    @staticmethod
    def quickPrim(graph):
        pass

    @staticmethod
    def kruskal(graph):
        pass

    @staticmethod
    def dijkstra(graph):
        pass

    @staticmethod
    def bellman_Ford(graph):
        pass


if __name__ == "__main__":
    baseDir = "D:\\work_space\\Algorithm_practice_py\\AlgorithmTraining\\data_structure\\Alg4th\\base_knowledge\\"
    unDirectedLoopGraph = baseDir + "undirectedGraphWithLoop"
    graphList = GraphUtil.getDataForUnWeightedGraph(unDirectedLoopGraph)
    UnDirectedGraph(graphList)