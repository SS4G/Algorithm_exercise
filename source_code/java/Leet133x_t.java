package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/13.
 */


import java.util.*;
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}

class Leet133x {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Map<UndirectedGraphNode, Integer> firstMark = new HashMap<>();
        dfsHelper(firstMark, node);
        Map<Integer, UndirectedGraphNode> clonedMark = new HashMap<>();
        for (UndirectedGraphNode node0 : firstMark.keySet()) {
            int label = node.label;
            clonedMark.put(label, new UndirectedGraphNode(label));
        }

        for (UndirectedGraphNode node0 : firstMark.keySet()) {
            int label = node.label;
            for (UndirectedGraphNode adjNode : node0.neighbors) {
                int adjLabel = firstMark.get(adjNode);
                clonedMark.get(label).neighbors.add(clonedMark.get(adjLabel));
            }
        }

        return clonedMark.get(firstMark.get(node));
    }

    public void dfsHelper(Map<UndirectedGraphNode, Integer> marked, UndirectedGraphNode node) {
        if (!marked.containsKey(node)) {
            marked.put(node, node.label);
            for (UndirectedGraphNode adjNode : node.neighbors) {
                dfsHelper(marked, adjNode);
            }
        }
    }
}

public class Leet133x_t {
    public static void main(String[] args) {
        UndirectedGraphNode n0 = new UndirectedGraphNode(0);
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        n0.neighbors.add(n1);
        n1.neighbors.add(n0);
        Leet133x leet = new Leet133x();
        leet.cloneGraph(n0);
    }
}
