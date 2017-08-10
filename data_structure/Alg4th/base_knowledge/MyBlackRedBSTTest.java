package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by BUPT_SS4G on 2017/8/10.
 *
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
        Node<K, V> newRoot = null;
        boolean putSubTreeFlag = false;
        if (key.compareTo(root.key) > 0) {
            putSubTreeFlag = true;
            root.left = put(root.right, key, value);
        }
        else if (key.compareTo(root.key) < 0) {
            putSubTreeFlag = true;
            root.right = put(root.left, key, value);
        }
        else {
            root.value = value;
        }
        if (putSubTreeFlag) {
            if (!isRed(root.left) && isRed(root.right)) { //右红左黑
                newRoot = rotateToLeft(root);
            }
            if (isRed(newRoot.left) && isRed(newRoot.left.left)) {
                newRoot = rotateToRight(newRoot);
            }
            if (isRed(newRoot.left) && isRed(newRoot.right)) {
                flipColors(newRoot);
            }
            return newRoot;
        }
        return root;
    }

    public void put(K key, V value) {
        if (headRoot == null) {
            headRoot = new Node<K, V>(key, value, false);
        }
        else {
            put(headRoot, key, value);
        }
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

    }
}
