package AlgorithmTraining.data_structure.Alg4th.base_knowledge;

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

    void insert(T0 val) {
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
                else {
                    break;
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

    public void removeMin(Node<T0> root) {
        if (root.left != null) {
            Node<T0> cur = root;
            while (cur.left.left != null) {
                cur = cur.left;
            }
            cur.left = cur.left.right;
        }
    }

    public void remove(T0 val) {
        Node<T0> fakeRoot = new Node<T0>(null);
        fakeRoot.left = root;
        boolean isLeft = true;
        Node<T0> lastNode = fakeRoot;
        Node<T0> tobeRemove = root;

        while (!tobeRemove.val.equals(val)) {
            System.out.println("finding");
            if (tobeRemove.val.compareTo(val) > 0) {
                lastNode = tobeRemove;
                isLeft = true;
                tobeRemove = tobeRemove.left;
            }
            else if (tobeRemove.val.compareTo(val) < 0) {
                lastNode = tobeRemove;
                isLeft = false;
                tobeRemove = tobeRemove.right;
            }
            else {
                break;
            }
        } // find Node which is tobeRemoved

        if (tobeRemove.left == null && tobeRemove.right == null) {
            tobeRemove = null;
        }
        else if (tobeRemove.left == null || tobeRemove.right == null) {
            tobeRemove = tobeRemove.left == null ? tobeRemove.right : tobeRemove.left;
        }
        else {
            Node<T0> cur = tobeRemove.right; //findMin Node of right Tree
            if (cur.left == null) {
                tobeRemove.val = cur.val;
                tobeRemove.right = cur.right;
            }
            else {
                while (cur.left.left != null) {
                    cur = cur.left;
                }
                tobeRemove.val = cur.left.val;
                removeMin(tobeRemove.right);
            }
        }
        if (isLeft) {
            lastNode.left = tobeRemove;
        }
        else {
            lastNode.right = tobeRemove;
        }
        root = fakeRoot.left;
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
    public static boolean isSorted(List<? extends Comparable> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).compareTo(arr.get(i + 1)) > 0)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        Random r = new Random();
        int[] arr = {4, 2, 6, 1, 3, 5, 7};
        for (int i: arr) {
            tree.insert(i);
        }
        for (Integer k: tree) {
            System.out.println(k);
        }
        System.out.println("------");
        //tree.remove(1);
        //tree.remove(2);
        //tree.remove(3);
        tree.remove(4);
        for (Integer k: tree) {
            System.out.println(k);
        }
    }
}
