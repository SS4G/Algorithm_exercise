package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/8/26.
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
            //relations.get(pair.get(1)).add(pair.get(0));

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


}

class WeighedDiGraphTest {
    public static void main(String[] args) {

    }
}
