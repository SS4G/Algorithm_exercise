package AlgorithmTraining.G55Utils.Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by BUPT_SS4G on 2017/7/17.
 */

public class TreeUtil {
    public static final int NULL_VAL = Integer.MIN_VALUE;

    public static ArrayList<TreeNode> serialize(TreeNode root) {
        ArrayList<TreeNode> serializeFifo = new ArrayList<>(10000);
        serializeFifo.add(root);
        int ptrOffset = 0;
        TreeNode ptr = null;
        while (ptrOffset < serializeFifo.size()) {
            ptr = serializeFifo.get(ptrOffset);
            if (ptr != null) {
                serializeFifo.add(ptr.left);
                serializeFifo.add(ptr.right);
            }
            ptrOffset++;
        }
        int tail = serializeFifo.size() - 1;
        while (tail >= 0 && serializeFifo.get(tail) == null)
            tail --;
        if (tail == -1)
            return new ArrayList<TreeNode>();
        return serializeFifo;
    }

    public static Integer[] treeToIntegerArr(TreeNode root) {
        ArrayList<TreeNode> arr = serialize(root);
        Integer[] intArr = new Integer[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == null)
                intArr[i] = null;
            else
                intArr[i] = arr.get(i).val;
        }
        return intArr;
    }

    @SuppressWarnings("unchecked")
    public static TreeNode deserialize(Integer[] arr) {
        List<Integer> li = Arrays.asList(arr);
        return deserialize(li);
    }

    public static TreeNode deserialize(int[] arr) {
        System.out.println("Integer.MIN_VALUE represent null node");
        Integer[] arr0 = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == Integer.MIN_VALUE) {
                arr0[i] = null;
            }
            else {
                arr0[i] = arr[i];
            }
        }
        return deserialize(arr0);
    }

    public static TreeNode deserialize(List<Integer> arr) {
        if (arr.get(0) == null)
            return null;
        ArrayList<TreeNode> ser = new ArrayList<>(5000);
        for (Integer i: arr) {
            ser.add(i == null ? null : new TreeNode(i));
        }
        // node array ready
        TreeNode rootPtr = ser.get(0);
        int rootIndex = 0;
        int addIndex = 1;
        while (rootIndex < arr.size()) {
            TreeNode curRoot = ser.get(rootIndex);
            if (curRoot != null) {
                if (addIndex < ser.size()) {
                    curRoot.left = ser.get(addIndex);
                    addIndex++;

                    if (addIndex < ser.size()) {
                        curRoot.right = ser.get(addIndex);
                        addIndex++;
                    }
                }
            }
            rootIndex++;
        }
        return rootPtr;
    }

    public static void showTree(TreeNode root) {
        showTreeRecuresive(root, "");
    }

    private static void showTreeRecuresive(TreeNode root, String levelPrefix) {
        if (root == null) {
            System.out.println(levelPrefix + ":null");
        }
        else {
            System.out.println(levelPrefix + ":" + root.val);
            showTreeRecuresive(root.left, levelPrefix + "----");
            showTreeRecuresive(root.right, levelPrefix + "----");
        }
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);

        n0.left = n1;
        n0.right = n2;
        n2.left = n3;
        n2.right = n4;
        //System.out.println(serialize(n0));
        //System.out.println(serialize(null));
        Integer[] x = treeToIntegerArr(n0);
        TreeNode root = deserialize(Arrays.asList(x));
        System.out.println(root.left.val);
        System.out.println(root.right.val);
        System.out.println(root.right.left.val);
        System.out.println(root.right.right.val);
        System.out.println(root.left.left);
        System.out.println(root.left.right);

    }
}
