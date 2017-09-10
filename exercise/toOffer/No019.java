package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/9.
 */
import AlgorithmTraining.G55Utils.Java.*;
import java.util.*;
class Solution019 {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return false;
        else
            return HasSubtreeRecur(root1, root2);
    }
    private boolean HasSubtreeRecur(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        else if (root1 == null || root2 == null) {
            return false;
        }
        else {
            boolean result = isSubTreeFromRoot(root1, root2);
            if (!result)
                result = HasSubtreeRecur(root1.left, root2);
            if (!result)
                result = HasSubtreeRecur(root1.right, root2);
            return result;
        }

    }

    private boolean isSubTreeFromRoot(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        else {
            if (root1 == null)
                return false;
            if (root1.val == root2.val)
                return isSubTreeFromRoot(root1.left, root2.left) && isSubTreeFromRoot(root1.right, root2.right);
            else
                return false;
        }
    }
}

public class No019 {
    public static void main(String[] args) {
        Solution019 s = new Solution019();
        Integer[] tree0 = {8,8,7,9,3,null,null,null,null,4,7};
        //Integer[] tree0 = {8,9,3,null,null,4,7};
        Integer[] tree1 = {8,9,2};
        TreeNode root0 = TreeUtil.deserialize(Arrays.asList(tree0));
        TreeNode root1 = TreeUtil.deserialize(Arrays.asList(tree1));
        System.out.println(s.HasSubtree(root0, root1));
    }
}
