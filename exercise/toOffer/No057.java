package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/9/13.
 */

class Solution057 {
    boolean isSymmetrical(TreeNode pRoot)
    {
        if (pRoot == null)
            return true;
        if (pRoot.left == null && pRoot.right == null)
            return true;
        else {
            return isSame(pRoot.left, pRoot.right);
        }
    }

    boolean isSame(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) {
            return true;
        }
        else if (leftRoot == null || rightRoot == null) {
            return false;
        }
        else if (leftRoot.val == rightRoot.val) {
            return isSame(leftRoot.left, rightRoot.right) && isSame(leftRoot.right, rightRoot.left);
        }
        else
            return false;
    }
}

public class No057 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 2, 3, 4, 4, 3, 5, 6, 5, 8, 8, 7, 6, 5};
        TreeNode root = TreeUtil.deserialize(arr);
        Solution057 s = new Solution057();
        System.out.println(s.isSymmetrical(root));
    }
}
