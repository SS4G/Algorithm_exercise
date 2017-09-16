package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.TreeNode;
import AlgorithmTraining.G55Utils.Java.TreeUtil;

import java.util.*;
import java.util.Stack;

/*
 * Created by szh-920 on 17-9-9.
 */

class Solution023 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<TreeNode> fifo = new ArrayList<>();
        int rd = 0;
        if (root == null) {
            return new ArrayList<>();
        }
        else {
            ArrayList<Integer> result = new ArrayList<>();
            fifo.add(root);
            while (rd < fifo.size()) {
                result.add(fifo.get(rd).val);
                if (fifo.get(rd).left != null) {
                    fifo.add(fifo.get(rd).left);
                    //rd++;
                }
                if (fifo.get(rd).right != null) {
                    fifo.add(fifo.get(rd).right);
                    //rd++;
                }
                rd++;
            }
            return result;
        }
    }
}

public class No023 {
    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6};
        TreeNode root = TreeUtil.deserialize(arr);
        Solution023 s = new Solution023();
        System.out.println(s.PrintFromTopToBottom(root));
    }
}
