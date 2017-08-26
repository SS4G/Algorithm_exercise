package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;
import java.util.*;
/**
 *
 * Created by BUPT_SS4G on 2017/8/21.
 */
interface UnionFind {
    boolean find(int v, int w);

    void union(int v, int w);
}

class QuickFind implements UnionFind {
    private HashMap<Integer, Integer> record = new HashMap<>();
    QuickFind(Set<Integer> keys) {
        for (Integer i : keys) {
            record.put(i, i);
        }
    }

    //return true is v w in same union
    public boolean find(int v, int w) {
        return record.get(v).equals(record.get(w));
    }

    public void union(int v, int w) {
        int staVal = record.get(v);
        int lastVal = record.get(w);
        for (int k : record.keySet()) {
            if (record.get(k) == lastVal) {
                record.put(k, staVal);
            }
        }
    }
}


class QuickUnion implements UnionFind {
    private HashMap<Integer, Integer> record = new HashMap<>();
    QuickUnion(Set<Integer> keys) {
        for (Integer i : keys) {
            record.put(i, i);
        }
    }

    public boolean find(int v, int w) {
        int ptr0 = v;
        int ptr1 = w;
        int tmp;
        while ((tmp = record.get(ptr0)) != ptr0) {
            ptr0 = tmp;
        }
        while ((tmp = record.get(ptr1)) != ptr1) {
            ptr1 = tmp;
        }
        return ptr0 == ptr1;
    }

    public void union(int v, int w) {
        int ptr0 = v;
        int ptr1 = w;
        int tmp;
        while ((tmp = record.get(ptr0)) != ptr0) {
            ptr0 = tmp;
        }
        while ((tmp = record.get(ptr1)) != ptr1) {
            ptr1 = tmp;
        }
        record.put(ptr0, ptr1);
    }
}

public class WeighedUnDiGraph extends UndirectedGraph {
    private HashMap<Integer, List<Edge>> edgesFrom;
    private HashMap<Integer, List<Edge>> edgesTo;
    WeighedUnDiGraph(String dataFilePath) {
        super();
        List<List<Integer>> datas = readGraphData(dataFilePath);
        HashMap<Integer, List<Integer>> relations = new HashMap<>();

        edgesFrom = new HashMap<>();
        edgesTo = new HashMap<>();

        for (List<Integer> pair : datas) {
            if (!relations.containsKey(pair.get(0))) {
                relations.put(pair.get(0), new ArrayList<>());
            }
            if (!relations.containsKey(pair.get(1))) {
                relations.put(pair.get(1), new ArrayList<>());
            }
            relations.get(pair.get(0)).add(pair.get(1));
            relations.get(pair.get(1)).add(pair.get(0));

            if (!edgesFrom.containsKey(pair.get(0))) {
                edgesFrom.put(pair.get(0), new ArrayList<>());
            }
            if (!edgesTo.containsKey(pair.get(1))) {
                edgesTo.put(pair.get(1), new ArrayList<>());
            }
            Edge tmpEdge = new Edge(pair.get(0), pair.get(1), pair.get(2));
            edgesFrom.get(pair.get(0)).add(tmpEdge);
            edgesTo.get(pair.get(1)).add(tmpEdge);
        }
        storage = relations;
        size = storage.size();
    }

    public List<Edge> primMST_Lazy() {
        //assume the whole graph is connected
        PriorityQueue<Edge> pq = new PriorityQueue<>(); //维护横切边集合
        HashSet<Integer> inMST = new HashSet<>();
        List<Integer> nodeList = new ArrayList<>(getAllNodes());
        List<Edge> primeMST = new ArrayList<>();
        Integer first = nodeList.get(0);
        inMST.add(first); //首先随便找出一个点

        //把这个点相连的所有边都放入优先队列中
        for (Edge e : edgesFrom.get(first)) {
            pq.offer(e);
        }

        for (Edge e : edgesTo.get(first)) {
            pq.offer(e);
        }

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();//回去当前优先队列中的最小的边
            int v = minEdge.eitherNode();
            int w = minEdge.otherNode(v);
            if (!(inMST.contains(v) && inMST.contains(w))) { //如果这条边的两端不全在已生成的MST中
                int otherNode = inMST.contains(v) ? w : v;//获取不在MST中的那个端点
                inMST.add(otherNode); //将这个端点添加到MST
                primeMST.add(minEdge);//将这条边添加到MST
                //检查与新添加顶点相关的边 如果另一端不在MST 就把这条边添加到优先队列中
                if (edgesFrom.containsKey(otherNode)) {
                    for (Edge e : edgesFrom.get(otherNode)) {
                        if (!inMST.contains(e.otherNode(otherNode))) //因为后来加入的边可能导致之前加入的横切边已经不是横切边了
                            pq.offer(e);
                    }
                }

                if (edgesTo.containsKey(otherNode)) {
                    for (Edge e : edgesTo.get(otherNode)) {
                        if (!inMST.contains(e.otherNode(otherNode)))
                            pq.offer(e);
                    }
                }
            }
        }
        return primeMST;
    }

    public List<Edge> kruskalMST() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        HashSet<Integer> inMST = new HashSet<>();
        List<Edge> kruskalMST = new ArrayList<>();
        for (List<Edge> someEdges : edgesFrom.values()) {
            pq.addAll(someEdges);
        }
        //UnionFind uf = new QuickFind(storage.keySet());
        UnionFind uf = new QuickUnion(storage.keySet());
        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();//获取所有边中的最小边
            int v = minEdge.eitherNode();
            int w = minEdge.otherNode(v);
            if (!uf.find(v, w)) { //如果当前最小边的两个端点不全在MST中 就把这条边加入MST
                uf.union(v, w);
                if (!inMST.contains(v))
                    inMST.add(v);
                if (!inMST.contains(w))
                    inMST.add(w);
                kruskalMST.add(minEdge);
            }
        }
        return kruskalMST;
    }
}

class WeighedUnDiGraphTest {
    public static void main(String[] args) {
        String baseDir = "D:\\work_space\\Algorithm_training_java\\src\\AlgorithmTraining\\data_structure\\Alg4th\\base_knowledge\\Graphs\\dataFile\\";
        String wugDataFile = baseDir + "weighedUnDiGraph";
        WeighedUnDiGraph wug = new WeighedUnDiGraph(wugDataFile);
        List<Edge> primMST = wug.primMST_Lazy();
        List<Edge> kruskalMST = wug.kruskalMST();
        System.out.println("Prim    MST:" + primMST);
        System.out.println("kruskal MST:" + kruskalMST);
    }
}
