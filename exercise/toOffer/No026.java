package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by BUPT_SS4G on 2017/9/9.
 */

class Solution026 {
    public int TreeDepth(TreeNode root) {
        return getDepth(root);
    }
    private int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        else
            return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}
public class No026 {
    public static void main(String[] args) {
        Solution026 s = new Solution026();
    }
}
