package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.util.*;
import java.util.List;
import java.util.Set;

/**
 * Created by BUPT_SS4G on 2017/8/21.
 */
public interface Graph {
    List<Integer> getAdjNodes(int value);

    void addEdge(int v0, int v1);

    List<Integer> bfs(); //

    List<Integer> dfs(); //

    boolean hasLoop();

    int size();
}
