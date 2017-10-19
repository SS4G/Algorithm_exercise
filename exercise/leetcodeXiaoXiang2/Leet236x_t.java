package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;

import java.util.*;

/**
 * Created by G5501 on 2017/10/15.
 */

class Leet236x {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        LinkedList<TreeNode> pathStack = new LinkedList<>();
        List<List<TreeNode>> twoPath = new ArrayList<>();
        twoPath.add(new ArrayList<>());
        twoPath.add(new ArrayList<>());
        preOrderTrverse(root, pathStack, p, q, twoPath);
        TreeNode ptr0 = twoPath.get(0).get(0);
        TreeNode ptr1 = twoPath.get(1).get(0);
        TreeNode ptrRes = null;
        int i = 0;
        while (i < Math.min(twoPath.get(0).size(), twoPath.get(1).size()) && twoPath.get(0).get(i) == twoPath.get(1).get(i)) {
            ptrRes = twoPath.get(0).get(i);
            i++;
        }
        return ptrRes;
    }

    public void preOrderTrverse(TreeNode root, LinkedList<TreeNode> pathStack, TreeNode p, TreeNode q, List<List<TreeNode>> pathRec) {
        if (root != null) {
            pathStack.addLast(root);
            if (root == p) {
                pathRec.set(0, new ArrayList<>(pathStack));
            }
            if (root == q) {
                pathRec.set(1, new ArrayList<>(pathStack));
            }
            preOrderTrverse(root.left, pathStack, p, q, pathRec);
            preOrderTrverse(root.right, pathStack, p, q, pathRec);
            pathStack.removeLast();
        }
    }
}

public class Leet236x_t {
    public static void main(String[] args) {
        Leet236x leet = new Leet236x();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        TreeNode root = TreeUtil.deserialize(arr);
        TreeNode p1 = root.left.right.left;
        TreeNode p2 = root.left.left.left;
        assert leet.lowestCommonAncestor(root, p1, p2).val == 2;
    }
}
