package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.io.*;
import java.util.*;


/**
 * Created by BUPT_SS4G on 2017/8/19.
 *
 */

class UndirectedGraph {
    //private HashMap<Integer, List<Integer>> storage;
    HashMap<Integer, List<Integer>> storage;
    private int nodeAmount;
    UndirectedGraph(List<List<Integer>> values) {
        storage = new HashMap<>();
        for (List<Integer> pair : values) {
            //System.out.println(pair);
            addEdge(pair.get(0), pair.get(1));
        }
        nodeAmount = storage.size();
    }
    
    public List<Integer> getAdj(Integer value) {
        return storage.get(value);
    }    
    
    public int getNodeAmount() {
        return nodeAmount;
    }
    
    public void addEdge(int v0, int v1) { //无向图中添加边是双向的
        //System.out.println(v0 + ":" + v1);
        //storage.get(v1);
        if (!storage.containsKey(v0)) {
            storage.put(v0, new ArrayList<Integer>());
        }
        storage.get(v0).add(v1);


        if (!storage.containsKey(v1)) {
            storage.put(v1, new ArrayList<Integer>());
        }
        storage.get(v1).add(v0);

        //System.out.println("v0:" + v0 + ":" + storage.get(v0));
        //System.out.println("v1:" + v1 + ":" + storage.get(v1));
    }

    public Set<Integer> getNodeValues() {
        return storage.keySet();
    }
}

class UndirectedGraphUtil {
    //返回dfs遍历序列
    public List<Integer> dfs(UndirectedGraph graph) {
        int amount = graph.getNodeAmount();
        HashSet<Integer> checked = new HashSet<>();
        List<Integer> output = new ArrayList<>();
        if (amount > 0) {
            dfsHelper(graph, output, checked, 0);
        }
        return output;
    }

    private void dfsHelper(UndirectedGraph graph, List<Integer> output, Set<Integer> checked, int curValue) {
        output.add(curValue);
        checked.add(curValue);
        List<Integer> adjance = graph.getAdj(curValue);
        //System.out.println(adjance);
        //System.out.println(graph.storage.get(0));
        for (Integer value : adjance) {
            if (!checked.contains(value)) {
                dfsHelper(graph, output, checked, value);
            }
        }
    }

    //返回无向图的BFS遍历序列
    public List<Integer> bfs(UndirectedGraph graph) {
        List<Integer> fifo = new ArrayList<>(256);
        HashSet<Integer> checked = new HashSet<>();
        int readIdx = 0;
        if (graph.getNodeAmount() > 0) {
            fifo.add(0);
            checked.add(0);
            while (readIdx < fifo.size()) {
                List<Integer> adjance = graph.getAdj(fifo.get(readIdx));
                readIdx++;
                for (int i : adjance) {
                    if (!checked.contains(i)) {
                        fifo.add(i);
                        checked.add(i);
                    }
                }
            }
        }
        return fifo;
    }

    public boolean hasLoop(UndirectedGraph graph) {
        Set<Integer> checked = new HashSet<>();
        if (graph.getNodeAmount() > 0) {
            return dfsLoopFinder(graph, checked, 0, 0);
        }
        else
            return false;
    }

    private boolean dfsLoopFinder(UndirectedGraph graph, Set<Integer> checked, int curValue, int lastValue) {
        List<Integer> adjance = graph.getAdj(curValue);
        checked.add(curValue);
        boolean res = false;
        for (Integer value : adjance) {
            if (!checked.contains(value)) {
                res = (res || dfsLoopFinder(graph, checked, value, curValue));
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
}

public class UndirectedGraphTest {
    private static List<List<Integer>> readGraphData(String dataFilePath) {
        File f = new File(dataFilePath);
        List<List<Integer>> datas = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 1) {
                    //System.out.println(line);
                    //line = line.trim();
                    String[] number = line.split("\\s+");
                    int a0 = Integer.parseInt(number[0]);
                    int a1 = Integer.parseInt(number[1]);
                    ArrayList<Integer> arr = new ArrayList<>(2);
                    arr.add(a0);
                    arr.add(a1);
                    datas.add(arr);
                }
            }
            fr.close();
            br.close();
        }
        catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return datas;
    }

    public static void main(String[] args) {
        String baseDir = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\data_structure\\Alg4th\\base_knowledge\\Graphs\\";
        String UndirectedGraphWithLoopPath = baseDir + "undirectedGraphWithLoop";
        String UndirectedGraphWithoutLoopPath = baseDir + "undirectedGraphWithoutLoop";

        List<List<Integer>> graphDataWithLoop = readGraphData(UndirectedGraphWithLoopPath);
        List<List<Integer>> graphDataWithoutLoop = readGraphData(UndirectedGraphWithoutLoopPath);

        UndirectedGraph graphWithLoop = new UndirectedGraph(graphDataWithLoop);
        UndirectedGraph graphWithoutLoop = new UndirectedGraph(graphDataWithoutLoop);

        UndirectedGraphUtil utl = new UndirectedGraphUtil();

        System.out.println("with loop:" + utl.dfs(graphWithLoop));
        System.out.println("without Loop" + utl.dfs(graphWithoutLoop));

        assert utl.hasLoop(graphWithLoop): "WA";
        assert !utl.hasLoop(graphWithoutLoop): "WA";
    }
}


