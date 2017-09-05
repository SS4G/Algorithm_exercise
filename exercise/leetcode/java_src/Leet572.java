package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/9.
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Leet572 {
    public static void getSpicifyNode(int val, TreeNode root, List<TreeNode> result) {
        if (root.val == val) {
            result.add(root);
        }
        if (root.left != null)
            getSpicifyNode(val, root.left, result);
        if (root.right != null)
            getSpicifyNode(val, root.right, result);
    }

    public static boolean compareTree(TreeNode root0, TreeNode root1) {
        List<Integer> preOrderRecord0 = new LinkedList<Integer>();
        List<Integer> midOrderRecord0 = new LinkedList<Integer>();
        List<Integer> preOrderRecord1 = new LinkedList<Integer>();
        List<Integer> midOrderRecord1 = new LinkedList<Integer>();
        preOrderRecord(root0, preOrderRecord0);
        preOrderRecord(root1, preOrderRecord1);
        midOrderRecord(root0, midOrderRecord0);
        midOrderRecord(root1, midOrderRecord1);
        if(preOrderRecord0.size() != preOrderRecord1.size())
            return false;
        int length = preOrderRecord0.size();
        for (int i = 0; i < length; i++) {
            if (! preOrderRecord0.remove(0).equals(preOrderRecord1.remove(0)))
                return false;
            if (! midOrderRecord0.remove(0).equals(midOrderRecord1.remove(0)))
                return false;
        }
        return true;
    }

    public static void preOrderRecord(TreeNode root, List<Integer> record) {
        if (root!=null)
        {
            record.add(root.val);
            preOrderRecord(root.left, record);
            preOrderRecord(root.right, record);
        }
    }

    public static void midOrderRecord(TreeNode root, List<Integer> record) {
        if (root!=null)
        {
            preOrderRecord(root.left, record);
            record.add(root.val);
            preOrderRecord(root.right, record);
        }
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        List<TreeNode> subIn_s = new LinkedList<TreeNode>();

        getSpicifyNode(t.val, s, subIn_s);
        if (subIn_s.size() == 0) {
            return false;
        }
        else {
            for (TreeNode root : subIn_s) {
                if(compareTree(root, t))
                    return true;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        TreeNode a0 = new TreeNode(0);
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);

        TreeNode b1 = new TreeNode(1);
        TreeNode b3 = new TreeNode(3);
        TreeNode b4 = new TreeNode(4);
        TreeNode b5 = new TreeNode(5);

        a0.left = a1;
        a0.right = a2;
        a1.left = a3;
        a3.left = a4;
        a3.right = a5;

        b1.left = b3;
        b3.left = b4;
        b3.right = b5;

        System.out.println(isSubtree(a0, b1));

    }
}
