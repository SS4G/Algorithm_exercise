package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

import sun.security.ssl.SSLContextImpl;

import java.util.*;


/**
 * Created by 903 on 2017/8/8.
 */

class MyBinarySearchTree<T0 extends Comparable<T0>>
implements Iterable<T0> {
    private class Node<T extends Comparable<T>> {
        Node<T> left;
        Node<T> right;
        T val;
        Node(T val) {
            this.val = val;
        }
    }

    private Node<T0> root;

    void insertRoot(T0 val) {
        if (root == null) {
            root = new Node<>(val);
        }
        else {
            Node<T0> cur = root;
            do {
                if (cur.val.compareTo(val) > 0) {
                    if (cur.left == null) {
                        cur.left = new Node<>(val);
                        break;
                    }
                    else {
                        cur = cur.left;
                    }
                }
                else if (cur.val.compareTo(val) < 0) {
                    if (cur.right == null) {
                        cur.right = new Node<>(val);
                        break;
                    }
                    else {
                        cur = cur.right;
                    }
                }
            } while(true);
        }
    }

    private Node<T0> findMinNode(Node<T0> root) {
        Node<T0> cur = root;
        while (cur != null) {
            if (cur.left == null) {
                return cur;
            }
            else {
                cur = cur.left;
            }
        }
        return null;
    }

    private Node<T0> findMaxNode(Node<T0> root) {
        Node<T0> cur = root;
        while (cur != null) {
            if (cur.right == null) {
                return cur;
            }
            else {
                cur = cur.right;
            }
        }
        return null;
    }

    public T0 remove(T0 val) {
        return remove(val, root);
    }

    private T0 remove(T0 val, Node<T0> subRoot) {
        Node<T0> cur = subRoot;
        Node<T0> pre = subRoot;
        while (cur != null) {
            boolean isLeft = (pre.left == cur);
            if (cur.val == val) {
                if (cur.left == null && cur.right == null) {
                    if (isLeft) {
                        pre.left = null;
                    }
                    else {
                        pre.right = null;
                    }
                }
                else if (cur.left == null && cur.right != null) {
                    if (isLeft) {
                        pre.left = cur.right;
                    }
                    else {
                        pre.right = cur.right;
                    }
                }
                else if (cur.left != null && cur.right == null) {
                    if (isLeft) {
                        pre.left = cur.left;
                    }
                    else {
                        pre.right = cur.left;
                    }
                }
                else {
                    Node<T0> maxNode = findMaxNode(cur.left);
                    cur.val = maxNode.val;
                    remove(cur.val, cur.left);
                }
                return val;
            }
            else if (cur.val.compareTo(val) < 0) {
                pre = cur;
                cur = cur.right;
            }
            else {
                pre = cur;
                cur = cur.left;
            }
        }
        return null;
    }

    private void midTraverseRecursive(Node<T0> root, List<T0> output) {
        if (root != null) {
            midTraverseRecursive(root.left, output);
            output.add(root.val);
            midTraverseRecursive(root.right, output);
        }
    }

    private List<T0> getElements() {
        ArrayList<T0> res = new ArrayList<T0>(1024);
        midTraverseRecursive(root, res);
        return res;
    }

    public Iterator<T0> iterator() {
        return new Iterator<T0>() {
            List<T0> iterElements = getElements();
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < iterElements.size();
            }

            @Override
            public T0 next() {
                index++;
                return iterElements.get(index - 1);
            }
        };
    }
}

public class MyBinarySearchTreeTest {
    public static void main(String[] args) {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        Random r = new Random();
        int size = r.nextInt(20);
        for (int i = 0; i < )
    }
}
