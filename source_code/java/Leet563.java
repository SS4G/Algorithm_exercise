package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by Administrator on 2017/5/12.
 */
public class Leet563 {
    private static final int TILT = 0;
    private static final int SUM = 1;

    public static int findTilt(TreeNode root) {
        return findTiltAndSum(root)[TILT];
    }
    public static int[] findTiltAndSum(TreeNode root) {
        int[] result = new int[2];
        if (root == null) {
            result[TILT] = 0;
            result[SUM] = 0;
            return result;
        }
        int[] leftResult = findTiltAndSum(root.left);
        int[] rightResult = findTiltAndSum(root.right);
        result[TILT] = leftResult[TILT] + rightResult[TILT] +
                Math.abs(leftResult[SUM] - rightResult[SUM]);
        result[SUM] = leftResult[SUM] + rightResult[SUM] + root.val;
        return result;
    }
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        a1.left = a2;
        a1.right = a3;
        a2.left = a4;
        a3.right = a5;
        System.out.println(findTilt(a1));
    }
}

