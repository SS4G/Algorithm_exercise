package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Graphs;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/8/26.
 *
 */

class Element<E extends Comparable<E>> implements Comparable<Element<E>>{
    private E filedA;
    Element(E e) {
        filedA = e;
    }

    public int compareTo(Element<E> e) {
        return filedA.compareTo(e.filedA);
    }

    public E getFiled() {
        return filedA;
    }

    public void setFiled(E e) {
        filedA = e;
    }
}

public class IndexPriorityQueue<T extends Comparable<T>> {


    //private PriorityQueue<Element<T>> pq; //出队时会保证顺序 哪怕是你在插入了
    private PriorityQueue<Element<T>> pq; //出队时会保证顺序 哪怕是你在插入了
    // 某个元素以后修改了这个元素用于排序的关键字
    private HashMap<Integer, Element<T>> mapI2E; // index -> element
    private HashMap<Element<T>, Integer> mapE2I; // element -> index

    IndexPriorityQueue() {
        pq = new PriorityQueue<>(); //出队时会保证顺序 哪怕是你在插入了
        mapI2E = new HashMap<>();
        mapE2I = new HashMap<>();
    }

    public void insert(int k, T element) {
        if (mapI2E.containsKey(k)) {
            System.out.println("key already Exist!");
        }
        else {
            Element<T> tmpElement = new Element<T>(element);
            pq.offer(tmpElement);
            mapI2E.put(k, tmpElement);
            mapE2I.put(tmpElement, k);
        }
    }

    public void change(int k, T element) {
        Element<T> oldElement = mapI2E.get(k);//.setFiled(element);
        pq.remove(oldElement);
        oldElement.setFiled(element);
        pq.offer(oldElement);
    }

    public boolean contains(int k) {
        return mapI2E.containsKey(k);
    }

    public void delete(int k) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public T minElement() {
        return pq.peek().getFiled();
    }

    public int minIndex() {
        return mapE2I.get(pq.peek());
    }

    public int delMin() {
        Element<T> tmpElemenet = pq.poll();
        return mapE2I.get(tmpElemenet);
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }

    public int size() {
        return pq.size();
    }

    public T getElement(int idx) {
        return mapI2E.get(idx).getFiled();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : mapI2E.keySet()) {
            sb.append(i + ":" + mapI2E.get(i).getFiled() + ",");
        }
        return sb.toString();
    }
}

class IndexPqTest {
    public static void main(String[] args) {
        IndexPriorityQueue<Integer> ipq = new IndexPriorityQueue<>();
        ipq.insert(0, 3000);
        ipq.insert(1, 3001);
        ipq.insert(2, 3002);
        ipq.insert(3, 3003);
        ipq.insert(4, 3004);
        ipq.insert(5, 3005);
        ipq.insert(6, 3006);
        ipq.insert(7, 3007);

        ipq.change(0, 0);
        //ipq.delMin();
        ipq.change(2, 26);
        ipq.change(4, 38);

        //System.out.println("pq" + ipq.pq.peek().getFiled());
        //ipq.delMin();
        //System.out.println("pq" + ipq.pq.peek().getFiled());
        //ipq.delMin();
        //System.out.println("pq" + ipq.pq.peek().getFiled());
        //ipq.delMin();
        /*
        for (int i = 0; i < 10; i++) {
<<<<<<< HEAD

=======
            //System.out.println(10 - i);
            try {
                ipq.insert(10 - 1 - i + 1000, 10 - 1 - i + 1000);
                //System.out.println(10 - i);
            }
            catch (Exception e) {
                System.out.println("the key alreay exist!");
            }
            //ipq.insert(10 - i, Integer.toString(10 - i));
        }
        for (int k = 0; k < 10; k++) {
            if (k % 2 == 0) {
                ipq.change(k + 1000, 2 * k + 1000);
            }
>>>>>>> 1f1d711cbf438a561dcaaf4e321ae182ec0f0bb3
        }
        while (!ipq.isEmpty()) {
            //System.out.println(ipq.minElement());
            System.out.println(ipq.delMin());
            //ipq.delMin();
        }*/
    }
}
