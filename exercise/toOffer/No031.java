package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

/**
 * Created by G5501 on 2017/9/10.
 */

class Solution031 {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null)
            return null;
        TreeNode[] result = covertRecur(pRootOfTree);
        return result[0];
    }

    private TreeNode[] covertRecur(TreeNode root) {
        if (root == null)
            return new TreeNode[]{null, null};
        else {
            //System.out.println(root.val);
            TreeNode[] leftLinkedList = covertRecur(root.left);
            TreeNode[] rightLinkedList = covertRecur(root.right);
            TreeNode leftMaxNode = leftLinkedList[1];
            TreeNode rightMinNode = rightLinkedList[0]; // min,max
            TreeNode[] result = new TreeNode[2];
            if (leftMaxNode != null) {
                leftMaxNode.right = root;
                root.left = leftMaxNode;
                result[0] = leftLinkedList[0];
            }
            else {
                root.left = null;
                result[0] = root;
            }

            if (rightMinNode != null) {
                rightMinNode.left = root;
                root.right = rightMinNode;
                result[1] = rightLinkedList[1];
            }
            else {
                root.right = null;
                result[1] = root;
            }
            return result;
        }
    }
}

public class No031 {
    public static void main(String[] args) {
        Integer[] arr = {5, 3, 7, 1, 4, 6,8,null,2};
        Solution031 s = new Solution031();
        TreeNode head = s.Convert(TreeUtil.deserialize(arr));

        while (head != null) {
            System.out.println(head.val);
            head = head.right;
        }
    }
}
