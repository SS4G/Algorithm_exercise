package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/8/26.
 *
 */
public class IndexPriorityQueue<T extends Comparable<T>> {
    class Element<E extends Comparable<E>> {
        E filedA;
        Element(E e) {
            filedA = e;
        }

        public int compareTo(Element<E> e) {
            return filedA.compareTo(e.filedA);
        }

        public E getFiled() {
            return filedA;
        }
    }

    private PriorityQueue<Element<T>> pq; //出队时会保证顺序 哪怕是你在插入了
    // 某个元素以后修改了这个元素用于排序的关键字
    private HashMap<Integer, Element<T>> map; // index -> element
    private HashMap<Element<T>, Integer> map1; // element -> index

    IndexPriorityQueue() {
        pq = new PriorityQueue<>(); //出队时会保证顺序 哪怕是你在插入了
        map = new HashMap<>();
        map1 = new HashMap<>();
    }

    void insert(int k, T element) throws Exception {
        if (map.containsKey(k)) {
            throw new Exception("key already exist!");
        }
        Element<T> tmpElement = new Element<T>(element);
        pq.offer(tmpElement);
        map.put(k, tmpElement);
        map1.put(tmpElement, k);
    }

    void change(int k, T element) {
        map.get(k).filedA = element;
    }

    boolean contains(int k) {
        return map.containsKey(k);
    }

    void delete(int k) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    T min() {
        return pq.peek().getFiled();
    }

    int minIndex() {
        return map1.get(pq.peek());
    }

    int delMin() {
        Element<T> tmpElemenet = pq.poll();
        return map1.get(tmpElemenet);
    }

    boolean isEmpty() {
        return pq.isEmpty();
    }

    int size() {
        return pq.size();
    }
}

class IndexPqTest {
    public static void main(String[] args) {
        IndexPriorityQueue<String> ipq = new IndexPriorityQueue<>();
        for (int i = 0; i < 10; i++) {

        }
    }
}
