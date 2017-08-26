package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.util.*;


/**
 * Created by BUPT_SS4G on 2017/8/19.
 *
 */

public class UndirectedGraph extends AbstractGraph implements Graph {
    //private HashMap<Integer, List<Integer>> storage;

    UndirectedGraph(String dataFilePath) {
        generateGraph(dataFilePath);
    }

    UndirectedGraph(HashMap<Integer, List<Integer>> storage) {
        this.storage = storage;
    }

    UndirectedGraph() {
        this.storage = null;
        this.size = 0;
    }

    public void addEdge(int v0, int v1) { //无向图中添加边是双向的
        if (!storage.containsKey(v0)) {
            storage.put(v0, new ArrayList<Integer>());
        }
        storage.get(v0).add(v1);


        if (!storage.containsKey(v1)) {
            storage.put(v1, new ArrayList<Integer>());
        }
        storage.get(v1).add(v0);
    }

    public boolean hasLoop() {
        Set<Integer> checked = new HashSet<>();
        if (size() > 0) {
            return dfsLoopFinder(checked, 0, 0);
        }
        else
            return false;
    }

    private boolean dfsLoopFinder(Set<Integer> checked, int curValue, int lastValue) {
        List<Integer> adjance = getAdjNodes(curValue);
        checked.add(curValue);
        boolean res = false;
        for (Integer value : adjance) {
            if (!checked.contains(value)) {
                res = (res || dfsLoopFinder(checked, value, curValue));
                if (res) {
                    return true;
                }
            }
            else if (value == lastValue) {//和上一个点相邻 不算是有环 在无向图中是都如此
                continue;
            }
            else {
                return true;
            }
        }
        return res;
    }

    public void generateGraph(String dataFilePath) {
        storage = new HashMap<>();
        List<List<Integer>> values = readGraphData(dataFilePath);
        for (List<Integer> pair : values) {
            //System.out.println(pair);
            addEdge(pair.get(0), pair.get(1));
        }
        size = storage.size();
    }
}

class UndirectedGraphTest {
    public static void main(String[] args) {
        String baseDir = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\data_structure\\Alg4th\\base_knowledge\\Graphs\\dataFile\\";
        String UndirectedGraphWithLoopPath = baseDir + "undirectedGraphWithLoop";
        String UndirectedGraphWithoutLoopPath = baseDir + "undirectedGraphWithoutLoop";

        UndirectedGraph graphWithLoop = new UndirectedGraph(UndirectedGraphWithLoopPath);
        UndirectedGraph graphWithoutLoop = new UndirectedGraph(UndirectedGraphWithoutLoopPath);

        System.out.println("with loop dfs:" + graphWithLoop.dfs());
        System.out.println("without Loop dfs" + graphWithoutLoop.dfs());

        assert graphWithLoop.hasLoop(): "WA";
        assert !graphWithoutLoop.hasLoop(): "WA";

        System.out.println("with loop bfs" + graphWithLoop.bfs());
        System.out.println("without loop bfs" + graphWithoutLoop.bfs());
    }
}


