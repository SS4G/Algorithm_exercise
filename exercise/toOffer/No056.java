package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/9/13.
 */

class Solution056 {
    public TreeNode KthNode(TreeNode pRoot, int k)
    {
        TreeNode[] res = new TreeNode[1];
        int[] cnt = new int[1];
        cnt[0] = 0;
        res[0] = null;
        markTreeRecursive(pRoot, k, res, cnt);
        return res[0];
    }

    private void markTreeRecursive(TreeNode root, int k, TreeNode[] res, int[] cnt) {
        if (root != null) {
            markTreeRecursive(root.left, k, res, cnt);
            cnt[0]++;
            if (cnt[0] == k) {
                res[0] = root;
            }
            markTreeRecursive(root.right, k, res, cnt);
        }
    }
}
public class No056 {
    public static void main(String[] args) {

    }
}
