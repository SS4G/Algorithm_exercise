package AlgorithmTraining.data_structure.Alg4th.base_knowledge.Sort;

import java.util.*;

/**
 * Created by 903 on 2017/8/7.
 * minHeap
 * Author: g55
 */


class MyMinHeap<T extends Comparable<T>> implements Iterable<T> {
    //index from 0
    private int size = 0;
    //private Object[] stroage;
    Object[] stroage;
    MyMinHeap(T[] arr) {
        stroage = arr.clone();
        size = stroage.length;
        if (arr.length > 0) {
            for (int k = 0; k < stroage.length; k++) {
                heapifyRecursive(stroage.length - 1 - k);
            }
        }
    }

    public void showArray(Object[] oArray) {
        for (Object o: oArray) {
            System.out.print(o);
        }
        System.out.println("\n");
    }

    public int size() {
        return size;
    }

    /**
     *
     * @param startIndex 进行堆化的起始点
     * 这个函数只是保证从startIndex之后的点为顶点 是合法的最小堆
     * 每次从一个顶点开始堆化的时候可能涉及到递归调用
     */
    @SuppressWarnings("unchecked")
    private void heapifyRecursive(int startIndex) {
        int cur = startIndex;
        int left = getLeft(cur);
        int right = getRight(cur);
        if (left >= size) {
            ;
        }
        else if (right >= size) {
            if (((T)stroage[cur]).compareTo((T)stroage[left]) > 0) {
                exch(cur, left);
            }
        }
        else {
            if (((T)stroage[cur]).compareTo((T)stroage[left]) <= 0 &&
                    ((T)stroage[cur]).compareTo((T)stroage[right]) <= 0) {
            }
            else if (((T)stroage[cur]).compareTo((T)stroage[left]) <= 0 &&
                    ((T)stroage[cur]).compareTo((T)stroage[right]) > 0) {
                exch(cur, right);
                heapifyRecursive(right);
            }
            else if (((T)stroage[cur]).compareTo((T)stroage[left]) > 0 &&
                    ((T)stroage[cur]).compareTo((T)stroage[right]) <= 0) {
                exch(cur, left);
                heapifyRecursive(left);
            }
            else {
                int minIndex = ((T)stroage[right]).compareTo((T)stroage[left]) >= 0 ? left : right;
                exch(minIndex, cur);
                heapifyRecursive(minIndex);
            }
        }
    }

    private void exch(int i, int j) {
        Object tmp = stroage[i];
        stroage[i] = stroage[j];
        stroage[j] = tmp;
    }

    /**
     该最小堆的其实索引是从0开始的
     */
    private int getLeft(int index) {
        return  (index << 1) + 1;
    }

    private int getRight(int index) {
        return (index << 1) + 2;
    }

    private int getFather(int index) {
        return (index & 0x00000001) == 0 ? (index >> 1) - 1 : index >> 1;
    }

    /**
     * 插入最小堆得方法是 插入到堆数组的最后一个 然后由下到上进行上浮操作
     * 进行一次检测即可 复杂度O(lgn)
     * @param val 要插入的元素
     */
    @SuppressWarnings("unchecked")
    public void insertToHeap(T val) {
        //插入尾部 进行上浮
        if (size >= stroage.length) {
            Object[] tmp = new Object[size << 1];
            System.arraycopy(stroage, 0, tmp, 0, stroage.length);
            stroage = tmp;
        }
        stroage[size] = val;
        int cur = size;
        size++;


        while (cur > 0) {
            int fatherIndex = getFather(cur);
            if (((T)stroage[cur]).compareTo((T)stroage[fatherIndex]) > 0) {
                break;
            }
            else {
                exch(fatherIndex, cur);
                cur = fatherIndex;
            }
        }
    }

    /**
     * 从顶部弹出元素时 先弹出最顶部的元素 然后用堆数组中最后的一个元素放到顶部
     * 然后自顶向下的进行下沉操作 复杂度O(lgn)
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T removeFromTop() throws Exception {
        if (size <= 0)
            throw new Exception("index error!");
        T top = (T)stroage[0];
        stroage[0] = stroage[size - 1];
        size--;
        int cur = 0;
        do {
            int left = getLeft(cur);
            int right = getRight(cur);
            if (left >= size) {
                break;
            }
            else if (right >= size) {
                if (((T)stroage[cur]).compareTo((T)stroage[left]) > 0) {
                    exch(cur, left);
                }
                break;
            }
            else {
                T minElement = ((T)stroage[left]).compareTo((T)stroage[right]) < 0 ?
                        (T)stroage[left] : (T)stroage[right];
                if (minElement.compareTo((T)stroage[cur]) < 0) {
                    int minIdx;
                    if (((T)stroage[left]).compareTo((T)stroage[right]) > 0) {
                        minIdx = right;
                    }
                    else {
                        minIdx = left;
                    }
                    exch(minIdx, cur);
                    cur = minIdx;
                }
                else {
                    break;  // sink end
                }
            }
        } while(true);
        return top;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() { //虽然返回的是一个新的对象但是这个对象和产生他的对象
            // 有关系 这个新的对象可以修改器母体对象的状态
            @Override
            public boolean hasNext() {
                return size > 0;
            }

            @Override
            public T next() {
                try {
                    return removeFromTop();
                }
                catch (Exception e) {
                    System.out.print("index Error");
                }
                return null;
            }
        };
    }
}

public class MyHeapTest {
    private static class Element implements Comparable<Element> {
        int i = 0;
        Element(int i) {
            this.i = i;
        }

        @Override
        public int compareTo(Element o) {
            if (this.i > o.i) {
                return 1;
            }
            else if (this.i == o.i) {
                return 0;
            }
            else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "e:" + String.valueOf(i) + " ";
        }
    }

    private static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static Element[] genRandom(int size) {
        Element[] elements = new Element[size];
        Random r = new Random();
        for (int j = 0; j < size; j++) {
            elements[j] = new Element(r.nextInt(size * 10));
            //elements[j] = new Element(size - j);
        }
        return elements;
    }

    public static void main(String[] args) {

        final int testTurn = 1;
        for (int i = 0; i < testTurn; i++) {
            Random r = new Random();
            Element[] elements = genRandom(20);
            MyMinHeap<Element> minheap = new MyMinHeap<>(elements);
            int randomInsertSize = r.nextInt(10);
            for (int k = 0; k < randomInsertSize; k++) {
                minheap.insertToHeap(new Element(r.nextInt(10)));
            }
            Element[] sortedElements = new Element[30];
            Arrays.fill(sortedElements, new Element(Integer.MAX_VALUE));
            int idx = 0;
            for (Element e : minheap) {
                System.out.print(e);
                sortedElements[idx] = e;
                idx++;
            }
            assert isSorted(sortedElements): "WA";
        }

    }
}
