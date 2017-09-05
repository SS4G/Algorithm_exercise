package AlgorithmTraining.data_structure.Alg4th.base_knowledge.BST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by BUPT_SS4G on 2017/8/10.
 *
 */

/**
 * 关于左右旋转的问题 详见算法第四版280页 表示了2-节点 和3-节点 4-节点的转化状态图
 * 主要是要理解红河书和理论上的2-3查找树是一种什么样的关系
 * @param <K> 键
 * @param <V> 值
 */

class MyBlackRedBST<K extends Comparable<K>, V>
implements Iterable<K> {
    class Node<K0, V0> {
        boolean isRed;
        V0 value;
        K0 key;
        Node<K0, V0> left;
        Node<K0, V0> right;
        int nodeAmountOfTree;
        Node(K0 key, V0 value, boolean isRed) {
            this.isRed = isRed;
            this.value = value;
            this.key = key;
            left = null;
            right = null;
            nodeAmountOfTree = 1;
        }
    }

    private Node<K, V> headRoot;

    private boolean isRed(Node<K, V> node) {
        return node != null && node.isRed;
    }

    private Node<K, V> rotateToLeft(Node<K, V> node) {
        Node<K, V> nodeRight = node.right;
        node.right = nodeRight.left;
        nodeRight.left = node;
        return nodeRight;
    }

    private Node<K, V> rotateToRight(Node<K, V> node) {
        Node<K, V> node0 = node;
        Node<K, V> node1 = node.left;
        Node<K, V> node2 = node.left.left;
        node0.left = node1.right;
        node1.left = node2;
        node1.right = node0;
        return node1;
    }

    private void flipColors(Node<K, V> root) {
        root.isRed = true;
        root.left.isRed = false;
        root.right.isRed = false;
    }

    private Node<K, V> put(Node<K, V> root, K key, V value) {
        if (root == null) {
            return new Node<K, V>(key, value, true);
        }
        boolean putSubTreeFlag = false;
        if (key.compareTo(root.key) > 0) {
            putSubTreeFlag = true;
            root.right = put(root.right, key, value);
        }
        else if (key.compareTo(root.key) < 0) {
            putSubTreeFlag = true;
            root.left = put(root.left, key, value);
        }
        else {
            root.value = value;
        }

        if (putSubTreeFlag) {
            if (!isRed(root.left) && isRed(root.right)) { //右红左黑
                root = rotateToLeft(root);
            }
            if (isRed(root.left) && isRed(root.left.left)) {
                root = rotateToRight(root);
            }
            if (isRed(root.left) && isRed(root.right)) {
                flipColors(root);
            }
        }
        return root;
    }

    public void put(K key, V value) {
        if (headRoot == null) {
            headRoot = new Node<K, V>(key, value, false);
        }
        else {
            headRoot = put(headRoot, key, value);
        }
    }

    public V get(K key) {
        Node<K, V> cur = headRoot;
        while (cur != null) {
            if (cur.key.compareTo(key) > 0) {
                cur = cur.left;
            }
            else if (cur.key.compareTo(key) < 0) {
                cur = cur.right;
            }
            else {
                return cur.value;
            }
        }
        return null;
    }

    private void midTraverseRecursive(Node<K, V> root, List<K> output) {
        if (root != null) {
            midTraverseRecursive(root.left, output);
            output.add(root.key);
            midTraverseRecursive(root.right, output);
        }
    }

    private List<K> getElements() {
        ArrayList<K> res = new ArrayList<K>(1024);
        midTraverseRecursive(headRoot, res);
        return res;
    }

    public List<K> getKeys() {
        return getElements();
    }

    public Iterator<K> iterator() {
        return new Iterator<K>() {
            List<K> iterElements = getElements();
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < iterElements.size();
            }

            @Override
            public K next() {
                index++;
                return iterElements.get(index - 1);
            }
        };
    }
}

public class MyBlackRedBSTTest {
    public static void main(String[] args) {
        MyBlackRedBST<Integer, Character> bst = new MyBlackRedBST<>();
        Random r = new Random();
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 50; i++) {
            bst.put(r.nextInt(100), s.charAt(i % 26));
        }
        System.out.println(bst.getKeys());
        for (Integer i : bst.getKeys()) {
            System.out.println(i + ":" + bst.get(i));
        }
    }
}
