package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by BUPT_SS4G on 2017/9/9.
 *
 */

class Solution027 {
    public boolean IsBalanced_Solution(TreeNode root) {
        int[] status = getHeightAndBanlance(root);
        //System.out.println("Height="+status[1]+" is"+(status[0] == 1));
        return status[0] > 0;
    }

    private int[] getHeightAndBanlance(TreeNode root) {
        if (root == null)
            return new int[]{1, 0}; // isAVL, height
        else {
            int[] leftStatus = getHeightAndBanlance(root.left);
            int[] rightStatus = getHeightAndBanlance(root.right);
            if (leftStatus[0] == 0 || rightStatus[0] == 0)
                return new int[]{0, 0};
            int leftHeight = leftStatus[1];
            int rightHeight = rightStatus[1];
            int[] result = new int[2];
            if (Math.abs(leftHeight - rightHeight) <= 1) {
                result[1] = Math.max(leftStatus[1], rightStatus[1]) + 1;
                result[0] = 1;
            }
            else {
                result[0] = 0;
            }
            return result;
        }
    }
}

public class No027 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, null, null, null, null, 6};
        TreeNode root = TreeUtil.deserialize(arr);
        Solution027 s = new Solution027();
        System.out.println(s.IsBalanced_Solution(root));
    }
}
