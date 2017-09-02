package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/8/19.
 *
 */
public class DirectedGraph extends AbstractGraph implements Graph {
    protected Set<Edge> edges = new HashSet<>();
    DirectedGraph() {

    }

    DirectedGraph(String dataFilePath) {
        generateGraph(dataFilePath);
    }

    DirectedGraph(HashMap<Integer, List<Integer>> mapOfGraph) {
        storage = mapOfGraph;
        size = storage.size();
    }

    public void addEdge(int v0, int v1) { //无向图中添加边是双向的
        if (!storage.containsKey(v0)) {
            storage.put(v0, new ArrayList<Integer>());
        }

        storage.get(v0).add(v1);

        if (!storage.containsKey(v1)) {
            storage.put(v1, new ArrayList<Integer>()); //对于有向图 要处理好单项边
            // 保证所有节点在storage中都有对应
        }
    }

    public boolean hasLoop() {
        //System.out.println(storage);
        Set<Integer> checked = new HashSet<>();
        Set<Integer> pathTrack = new HashSet<>();
        if (size() > 0) {
            boolean hasLoop = false;
            for (int startValue : storage.keySet()) {
                hasLoop = hasLoop || dfsLoopFinder(checked, startValue, pathTrack);
            }
            return hasLoop;
        }
        else
            return false;
    }

    private boolean dfsLoopFinder(Set<Integer> checked, int curValue, Set<Integer> pathTrack) {
        //System.out.println(curValue);
        if (!checked.contains(curValue)) {
            List<Integer> adjance = getAdjNodes(curValue);
            checked.add(curValue);
            pathTrack.add(curValue);
            boolean res = false;
            for (Integer value : adjance) {
                if (pathTrack.contains(value))
                    return true;
                if (!checked.contains(value)) {
                    res = (res || dfsLoopFinder(checked, value, pathTrack));
                    if (res) {
                        return true;
                    }
                } //与原路径点相连对于有向图而言也是属于有环
            }
            pathTrack.remove(curValue);
            return res;
        }
        return false;
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

    //获取无环有向图的拓扑排序
    public List<Integer> topologySort() {
        if (!hasLoop()) {
            return getReversedPostOrderTraverse();
        }
        else
            return null;
    }

    //获取当前有向图的逆后序序列
    public List<Integer> getReversedPostOrderTraverse() {
        Set<Integer> checked = new HashSet<>();
        List<Integer> postOrder = new ArrayList<>();
        for (int i : storage.keySet()) {
            dfsPostOrderHelper(i, checked, postOrder);
        }
        Collections.reverse(postOrder);
        return postOrder;
    }

    private void dfsPostOrderHelper(int curValue, Set<Integer> checked, List<Integer> output) {
        if (!checked.contains(curValue)) {//这个条件为假会出现在外部第一次调用时
            List<Integer> adjance = getAdjNodes(curValue);
            checked.add(curValue);
            boolean res = false;
            for (Integer value : adjance) {
                if (!checked.contains(value)) {
                    dfsPostOrderHelper(value, checked, output);
                } //与原路径点相连对于有向图而言也是属于有环
            }
            output.add(curValue);
        }
    }

    //获得当前图的反
    public DirectedGraph getReversedGraph() {
        HashMap<Integer, List<Integer>> newMap = new HashMap<>();
        for (Integer key : storage.keySet()) {
            for (Integer value : storage.get(key)) {
                if (!newMap.containsKey(value)) {
                    newMap.put(value, new ArrayList<Integer>());
                }
                newMap.get(value).add(key);
            }
        }
        return new DirectedGraph(newMap);
    }

    //获取当前图中的强连通分量 Kosoraju 算法
    //用遍历反相图的逆后序序列来 遍历原图 每次首次调用dfs产生的序列 是一个强联通分量
    public List<List<Integer>> getStronglyConnected() {
        DirectedGraph reversedGraph = getReversedGraph();
        List<Integer> reversedPostOrder = reversedGraph.getReversedPostOrderTraverse();
        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
        Set<Integer> checked = new HashSet<>();
        for (int curValue : reversedPostOrder) {
            List<Integer> oneResult = new ArrayList<>();
            getOneStronglyConneted(true, curValue, checked, oneResult);
            if (oneResult.size() > 0) {
                stronglyConnectedComponents.add(oneResult);
            }
        }
        return stronglyConnectedComponents;
    }

    //获取其中的一个强连通分量 如果该点已经不是强连通分量 那么返回null
    private boolean getOneStronglyConneted(boolean isFirst, int curValue, Set<Integer> checked,
                                                 List<Integer> oneOutput) {
        if (isFirst && checked.contains(curValue)) { //当前的节点已经属于其他分量 不予考虑
            return false;
        }
        else { //当前的节点是第一个节点 是一个新的分量的开始
            oneOutput.add(curValue);
            checked.add(curValue);
            for (int value : getAdjNodes(curValue)) {
                if (!checked.contains(value)) {
                    getOneStronglyConneted(false, value, checked, oneOutput);
                }
            }
            return true;
        }
    }
}

class DirectedGraphTest {
    public static void main(String[] args) {
        String baseDir = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\data_structure\\Alg4th\\base_knowledge\\Graphs\\dataFile\\";
        String graphDataWithoutLoop = baseDir + "directedGraphWithoutLoop";
        String graphDataWithLoop = baseDir + "directedGraphWithLoop";
        DirectedGraph topologyGraph = new DirectedGraph(graphDataWithoutLoop);
        DirectedGraph strongConnectGraph = new DirectedGraph(graphDataWithLoop);

        System.out.println("topology Sort " + topologyGraph.topologySort());
        System.out.println("topology has loop " + topologyGraph.hasLoop());

        System.out.println("strongly Connect has loop " + strongConnectGraph.hasLoop());
        System.out.println("strongly Connect components " + strongConnectGraph.getStronglyConnected());
    }
}
