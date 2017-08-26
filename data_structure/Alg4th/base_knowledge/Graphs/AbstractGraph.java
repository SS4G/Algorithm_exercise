package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.io.*;
import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/8/21.
 *
 */

public abstract class AbstractGraph implements Graph {
    protected HashMap<Integer, List<Integer>> storage; //
    protected int size; // node amount in the graph

    public List<Integer> dfs() {
        int amount = size();
        HashSet<Integer> checked = new HashSet<>();
        List<Integer> output = new ArrayList<>();
        if (amount > 0) {
            dfsHelper(output, checked, 0);
        }
        return output;
    }

    private void dfsHelper(List<Integer> output, Set<Integer> checked, int curValue) {
        output.add(curValue);
        checked.add(curValue);
        List<Integer> adjance = getAdjNodes(curValue);
        for (Integer value : adjance) {
            if (!checked.contains(value)) {
                dfsHelper(output, checked, value);
            }
        }
    }

    public List<Integer> bfs() {
        List<Integer> fifo = new ArrayList<>(256);
        HashSet<Integer> checked = new HashSet<>();
        int readIdx = 0;
        if (size() > 0) {
            fifo.add(0);
            checked.add(0);
            while (readIdx < fifo.size()) {
                List<Integer> adjance = getAdjNodes(fifo.get(readIdx));
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

    public List<List<Integer>> readGraphData(String dataFilePath) {
        File f = new File(dataFilePath);
        List<List<Integer>> datas = new ArrayList<>();
        try {
            int column = 0;
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 1 && (!line.startsWith("#"))) {
                    String[] number = line.split("\\s+");
                    ArrayList<Integer> arr = new ArrayList<>(2);
                    for (int k = 0; k < column; k++) {
                        arr.add(Integer.parseInt(number[k]));
                    }
                    datas.add(arr);
                }
                else if (line.startsWith("#") && line.contains("column")) {
                    column = Integer.parseInt(line.trim().split("\\s+")[3]);
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

    public int size() {
        return size;
    }

    public List<Integer> getAdjNodes(int currentNode) {
        return storage.containsKey(currentNode) ? storage.get(currentNode) : new ArrayList();
    }

    protected abstract void generateGraph(String path);

    protected HashMap<Integer, List<Integer>> getStorage() {
        return storage;
    }

    public Set<Integer> getAllNodes() {
        return storage.keySet();
    }
}
