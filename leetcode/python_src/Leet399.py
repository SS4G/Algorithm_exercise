class Solution(object):
    def calcEquation(self, equations, values, queries):
        """
        :type equations: List[List[str]]
        :type values: List[float]
        :type queries: List[List[str]]
        :rtype: List[float]
        """
        n = len(equations)
        if n == 0:
            return []
        graph = {}
        # build graph
        for i in range(n):
            numrator = equations[i][0]
            denumrator = equations[i][1]
            graph.setdefault(numrator, {})
            graph.setdefault(denumrator, {})
            graph[numrator][denumrator] = (graph[denumrator], values[i])  # numrator / denumrator = values[i]
            graph[denumrator][numrator] = (graph[numrator], 1 / values[i])  # numrator / denumrator = values[i]
        result = []
        for q in queries:
            numrator = q[0]
            denumrator = q[1]
            if numrator == denumrator and numrator in graph:
                result.append(1.0)
                continue
            elif (numrator == denumrator) or (numrator not in graph) or (denumrator not in graph):
                result.append(-1.0)
                continue
            else:
                record = {k: False for k in graph}
                val = self.dfsHelper(graph, numrator, denumrator, record)
                result.append(val if val is not None else -1.0)
        return result

    def dfsHelper(self, graph, thisKey, targetKey, record):
        if thisKey not in record:
            return -1.0
        if record[thisKey] is True:
            return None

        record[thisKey] = True
        if thisKey == targetKey:
            return 1.0
        val = None
        for nextKey in graph[thisKey].keys():
            val = self.dfsHelper(graph, nextKey, targetKey, record)
            if val is None:
                continue
            elif val < 0:
                return -1.0
            else:
                return graph[thisKey][nextKey][1] * val
        return None if val is None else val


if __name__ == "__main__":
    s = Solution()
    equations = [["a", "b"], ["b", "c"]]
    values = [2.0, 3.0]
    queries = [["a", "c"], ["b", "c"], ["a", "e"], ["a", "a"], ["x", "x"]]
    res = s.calcEquation(equations, values, queries)
    print(res)
