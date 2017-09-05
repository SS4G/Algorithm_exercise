package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/8/26.
 *
 */

public class WeighedDiGraph extends DirectedGraph {
    private HashMap<Integer, List<Edge>> edgesFrom;
    private HashMap<Integer, List<Edge>> edgesTo;
    WeighedDiGraph(String dataFilePath) {
        super();
        List<List<Integer>> datas = readGraphData(dataFilePath);
        HashMap<Integer, List<Integer>> relations = new HashMap<>();

        edgesFrom = new HashMap<>();
        edgesTo = new HashMap<>();

        for (List<Integer> pair : datas) { //only set pair0 -> pair1
            if (!relations.containsKey(pair.get(0))) {
                relations.put(pair.get(0), new ArrayList<>());
            }
            if (!relations.containsKey(pair.get(1))) {
                relations.put(pair.get(1), new ArrayList<>());
            }
            relations.get(pair.get(0)).add(pair.get(1));

            if (!edgesFrom.containsKey(pair.get(0))) {
                edgesFrom.put(pair.get(0), new ArrayList<>());
            }
            if (!edgesTo.containsKey(pair.get(1))) {
                edgesTo.put(pair.get(1), new ArrayList<>());
            }
            Edge tmpEdge = new Edge(pair.get(0), pair.get(1), pair.get(2));
            edges.add(tmpEdge);
            edgesFrom.get(pair.get(0)).add(tmpEdge);
            edgesTo.get(pair.get(1)).add(tmpEdge);
        }
        storage = relations;
        size = storage.size();
    }
    
    List<Integer> dijkstra(int s, int d) {
        Map<Integer, Integer> preNode = new HashMap<>(); //表明当前点到源点的路径的上一个点
        IndexPriorityQueue<Integer> ipq = new IndexPriorityQueue<>();
        Set<Integer> inTree = new HashSet<>();
        final int NONE_PRE_NODE = -1;
        for (int nodeIdx : storage.keySet()) {
            ipq.insert(nodeIdx, Integer.MAX_VALUE); //init all nodes to the src node as infity
        }
        preNode.put(s, s);
        ipq.change(s, 0);

        //ipq.delMin();//remove source node
        while (!ipq.isEmpty()) {
            Integer newAddNode = ipq.delMin();
            inTree.add(newAddNode);
            for (Edge e : edgesFrom.get(newAddNode)) {
                int otherNode = e.otherNode(newAddNode);
                if (!inTree.contains(otherNode)) {
                    if (ipq.getElement(otherNode) >= ipq.getElement(newAddNode) + e.getWeight()) {
                        ipq.change(otherNode, ipq.getElement(newAddNode) + e.getWeight());
                        preNode.put(otherNode, newAddNode);
                    }
                }
            }
        }
        int tmpNode = d;
        List<Integer> pathTrack = new ArrayList<>();
        while (preNode.get(tmpNode) != tmpNode && preNode.get(tmpNode) != NONE_PRE_NODE) {
            pathTrack.add(tmpNode);
            tmpNode = preNode.get(tmpNode);
        }

        if (pathTrack.size() > 0) {
            pathTrack.add(s);
        }

        Collections.reverse(pathTrack);
        return pathTrack;
    }

    List<Integer> bellmanFord2(int s, int d) {
        Queue<Integer> fifo = new LinkedList<>();
        Set<Integer> inQueue = new HashSet<>();
        Map<Integer, Integer> distRec = new HashMap<>();
        Map<Integer, Integer> edgeFrom = new HashMap<>();
        for (int i : storage.keySet()) {
            distRec.put(i, 5000);
        }

        distRec.put(s, 0);
        edgeFrom.put(s, s);
        fifo.offer(s);
        inQueue.add(s);
        int cnt = 0;
        while (!fifo.isEmpty()) {
            int node = fifo.poll();
            inQueue.remove(node);
            for (Edge e : edgesFrom.get(node)) {
                int otherNode = e.otherNode(node);
                //System.out.println(e);
                if (distRec.get(otherNode) > e.getWeight() + distRec.get(node)) {
                    distRec.put(otherNode, e.getWeight() + distRec.get(node));
                    edgeFrom.put(otherNode, node);
                    if (!inQueue.contains(otherNode)) {
                        fifo.offer(otherNode);
                        inQueue.add(otherNode);
                    }
                }
                if (cnt++ % storage.size() == 0) {
                    if (hasNegWeighLoop(edgeFrom)) {
                        System.out.println("has neg weight loop");
                        return null;
                    }
                }

            }
            //System.out.println(distRec);
        }
        //System.out.println(edgeFrom);
        int tmpNode = d;
        List<Integer> pathTrack = new ArrayList<>();
        while (edgeFrom.get(tmpNode) != tmpNode && edgeFrom.get(tmpNode) != -1) {
            pathTrack.add(tmpNode);
            tmpNode = edgeFrom.get(tmpNode);
        }
        if (pathTrack.size() > 0) {
            pathTrack.add(s);
        }
        Collections.reverse(pathTrack);
        return pathTrack;
    }

    boolean hasNegWeighLoop(Map<Integer, Integer> edgeFrom) {
        for (int node : edgeFrom.keySet()) {
            Set<Integer> checked = new HashSet<>();
            while (edgeFrom.get(node) != node && edgeFrom.get(node) != -1) {
                if (checked.contains(node))
                    return true;
                checked.add(node);
                node = edgeFrom.get(node);
            }
        }
        return false;
    }

    List<Integer> bellmanFord1(int s, int d) {
        Map<Integer, Integer> distRec = new HashMap<>();
        Map<Integer, Integer> edgeFrom = new HashMap<>();
        final int NONE_PRE_NODE = -1;
        //System.out.println(edges);
        for (int node : storage.keySet()) {
            distRec.put(node, 5000); //初始值使用Integer是不能设置的过大 否则会溢出
            //如果使用double时应该使用 Inf这个特殊值
            edgeFrom.put(node, NONE_PRE_NODE);
        }
        distRec.put(s, 0);
        edgeFrom.put(0, 0);
        boolean lastTurn = false;
        for (int i = 0; i < size + 1; i++) { //v + 1 turn
            lastTurn = relax1Turn(distRec, edgeFrom);
            //System.out.println(distRec);
        }
        if (lastTurn) {
            System.out.println("has neg Loop!!");
            return null;
        }
        else {
            int tmpNode = d;
            List<Integer> pathTrack = new ArrayList<>();
            while (edgeFrom.get(tmpNode) != tmpNode && edgeFrom.get(tmpNode) != NONE_PRE_NODE) {
                pathTrack.add(tmpNode);
                tmpNode = edgeFrom.get(tmpNode);
            }

            if (pathTrack.size() > 0) {
                pathTrack.add(s);
            }

            Collections.reverse(pathTrack);
            return pathTrack;
        }
    }

    boolean relax1Turn(Map<Integer, Integer> distRec, Map<Integer, Integer> edgeFrom) {
        boolean modifyFlag = false;
        for (Edge e : edges) {
            int weight = e.getWeight();
            int src = e.eitherNode();
            int dst = e.otherNode(src);
            if (distRec.get(dst) > distRec.get(src) + weight) {
                distRec.put(dst, distRec.get(src) + weight);
                edgeFrom.put(dst, src);
                modifyFlag = true;
            }
        }
        return modifyFlag;
    }
}

class WeighedDiGraphTest {
    private static void dijkstraTest(String dataFilePath) {
        WeighedDiGraph wg = new WeighedDiGraph(dataFilePath);
        List<Integer> pathTo6 = wg.dijkstra(0, 6); // 0 -> 2 -> 7 -> 3 -> 6
        List<Integer> pathTo1 = wg.dijkstra(0, 1); // 0 -> 4 -> 5 -> 1
        System.out.println("0-->6 " + pathTo6);
        System.out.println("0-->1 " + pathTo1);
    }

    private static void bellmanFord1Test(String dataFilePath) {
        WeighedDiGraph wg = new WeighedDiGraph(dataFilePath);
        List<Integer> pathTo6 = wg.bellmanFord1(0, 6); // 0 -> 2 -> 7 -> 3 -> 6
        List<Integer> pathTo1 = wg.bellmanFord1(0, 1); // 0 -> 2 -> 7 -> 3 -> 6
        System.out.println(pathTo6);
        System.out.println(pathTo1);
    }

    private static void bellmanFord2Test(String dataFilePath) {
        WeighedDiGraph wg = new WeighedDiGraph(dataFilePath);
        List<Integer> pathTo6 = wg.bellmanFord2(0, 6);
        List<Integer> pathTo1 = wg.bellmanFord2(0, 1);
        System.out.println(pathTo6);
        System.out.println(pathTo1);
    }

    public static void main(String[] args) {
        String baseDir = "D:\\workSpace\\alg_java\\src\\AlgorithmTraining\\data_structure\\Alg4th\\base_knowledge\\Graphs\\dataFile\\";
        String diGraphDataFilePath = baseDir + "weighedDiGraph";
        //dijkstraTest(diGraphDataFilePath);
        String dGdataWithoutNegLoop = baseDir + "weighedDiGraphWithoutNegLoop";
        String dGdataWithNegLoop = baseDir + "weighedDiGraphWithNegLoop";

        //bellmanFord1Test(dGdataWithNegLoop);
        bellmanFord2Test(dGdataWithNegLoop);
        //bellmanFord1Test(dGdataWithoutNegLoop);
        bellmanFord2Test(dGdataWithoutNegLoop);
    }
}
