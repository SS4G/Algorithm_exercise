package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by BUPT_SS4G on 2017/10/16.
 */

class Leet538x {
    public TreeNode convertBST(TreeNode root) {
        int[] total = new int[1];
        rightPreOrderTraverse(root, total);
        return root;
    }

    public void rightPreOrderTraverse(TreeNode root, int[] totalSum) {
        if (root != null) {
            rightPreOrderTraverse(root.right, totalSum);
            root.val += totalSum[0];
            totalSum[0] = root.val;
            rightPreOrderTraverse(root.left, totalSum);
        }
    }
}

public class Leet538x_t {
    public static void main(String[] args) {
        TreeNode root = TreeUtil.deserialize(new int[]{5, 2, 7, 1, 4, 6, 9});
        Leet538x leet = new Leet538x();
        leet.convertBST(root);
        TreeUtil.showTree(root);
    }
}
