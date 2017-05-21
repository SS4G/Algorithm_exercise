package AlgorithmTraining.leetcode.java_src;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by BUPT_SS4G on 2017/5/10.
 */

public class Leet572_2 {
    boolean isSameTree(TreeNode root0, TreeNode root1) {
        if (root0 == null && root1 == null)
            return true;
        if (root0.val == root1.val) {
            return isSameTree(root0.left, root1.left) && isSameTree(root0.right, root1.right);
        }
        else
            return false;
    }
    boolean preOrderCompare(TreeNode mainTree, TreeNode t) {
        return isSameTree(mainTree, t)||preOrderCompare(mainTree.left, t) || preOrderCompare(mainTree.right, t);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return preOrderCompare(s,t);
    }
}
