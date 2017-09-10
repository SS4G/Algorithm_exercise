package AlgorithmTraining.exercise.toOffer;
import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/9/9.
 */

class Solution {
    public void Mirror(TreeNode root) {
        if (root != null) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            Mirror(left);
            Mirror(right);
            root.left = right;
            root.right = left;
        }
    }
}

public class No020 {
    public static void main(String[] args) {

    }
}
