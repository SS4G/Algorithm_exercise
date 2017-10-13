package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/10/12.
 */
class Leet114x {
    TreeNode flatten(TreeNode root) {
        return flattenRecure(root)[0];
    }

    TreeNode[] flattenRecure(TreeNode root) {
        TreeNode[] res = new TreeNode[2];
        if (root == null) {
            res[0] = null;
            res[1] = null;
        }
        else {
            TreeNode[] left = flattenRecure(root.left);
            TreeNode[] right = flattenRecure(root.right);
            res[0] = root;
            root.left = null;
            if (left[0] == null && right[0] == null) {
                res[1] = root;
            }
            else if (left[0] == null) {
                root.right = right[0];
                res[1] = right[1];
            }
            else if (right[0] == null) {
                root.right = left[0];
                res[1] = left[1];
            }
            else {
                root.right = left[0];
                left[1].right = right[0];
                res[1] = right[1];
            }
        }
        return res;
    }
}

public class Leet114x_t {
    public static void main(String[] args) {
        Leet114x leet = new Leet114x();
        TreeNode root = TreeUtil.deserialize(new int[]{1, 2, 5, 3, 4, TreeUtil.NULL_VAL, 6});
        TreeUtil.showTree(leet.flatten(root));
    }
}
