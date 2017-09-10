package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;

import java.util.*;

/**
 * Created by BUPT_SS4G on 2017/9/9.
 */

class Solution025 {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        LinkedList<Integer> pathStack = new LinkedList<>();
        if (root == null)
            return result;
        else {
            getPath(root, target, pathStack, 0, result);
            return result;
        }
    }

    private void getPath(TreeNode root, int tagetSum, LinkedList<Integer> pathStack, int lastSum, ArrayList<ArrayList<Integer>> output) {
        pathStack.push(root.val);
        if (root.left == null && root.right == null) {
            if (tagetSum == lastSum + root.val) {
                ArrayList<Integer> path = new ArrayList<Integer>(pathStack);
                Collections.reverse(path);
                output.add(path);
            }
        }
        else {
            if (root.left != null)
                getPath(root.left, tagetSum, pathStack, lastSum + root.val, output);
            if (root.right != null)
                getPath(root.right, tagetSum, pathStack, lastSum + root.val, output);
        }
        pathStack.pop();
    }
}

public class No025 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        Solution025 s = new Solution025();
        TreeNode root = TreeUtil.deserialize(arr);
        System.out.println(s.FindPath(root, 18));
    }
}
