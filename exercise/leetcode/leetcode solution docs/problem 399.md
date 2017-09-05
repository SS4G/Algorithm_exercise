## 399. Evaluate Division
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:

```
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
```

return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:


```
equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
```

#### tips
这个题目其实是个图的DFS 知道某些点之间的倍数关系 求另外一些点之间的倍数关系 其实就是根据已知的路径，对给定的起讫点的路径进行查找 题目假设不同路径只要起讫点相同结果必然相同， 找到了根据递归链将结果传回即可

#### mycode

```
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
        #print("this", thisKey, "tk", targetKey)
        if thisKey not in record:
            return -1.0
        if record[thisKey] is True:
            return None

        record[thisKey] = True
        if thisKey == targetKey:
            #print("FF")
            return 1.0
        val = None
        for nextKey in graph[thisKey].keys():
            #print("this key is ", thisKey, "nk", nextKey)
            val = self.dfsHelper(graph, nextKey, targetKey, record)
            if val is None:
                continue
            elif val < 0:
                return -1.0
            else:
                return graph[thisKey][nextKey][1] * val
        return None if val is None else val
```

