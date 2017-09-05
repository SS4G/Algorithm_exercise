package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

/**
 * Created by BUPT_SS4G on 2017/8/21.
 */
class Edge implements Comparable<Edge> { //v->w
    private final int weight;
    private final int v;
    private final int w;
    Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int compareTo(Edge e) {
        if (weight > e.weight)
            return 1;
        else if (weight == e.weight)
            return 0;
        else
            return -1;
    }

    public int getWeight() {
        return weight;
    }

    public int otherNode(int thisNode) {
        return thisNode != v ? v : w;
    }

    public int eitherNode() {
        return v;
    }

    public String toString() {
        return "[" + v + "->" + w + ":" + weight + "]"; //双向边
    }
}
