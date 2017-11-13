package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/10/15.
 */

class Leet232x {
    public int kthSmallest(TreeNode root, int k) {
        int[] output = new int[1];
        int[] cnt = new int[1];
        cnt[0] = 0;
        inorderTraverse(root, cnt, output, k);
        return output[0];
    }

    public boolean inorderTraverse(TreeNode root, int[] cnt, int[] output, int k) {
        if (root == null)
            return false;
        else {
            if (inorderTraverse(root.left, cnt, output, k))
                return true;
            if (cnt[0] + 1 == k) {
                output[0] = root.val;
                return true;
            }
            cnt[0]++;
            return inorderTraverse(root.right, cnt, output, k);
        }
    }
}

public class Leet230x_t {
    public static void main(String[] args) {

    }
}
