package AlgorithmTraining.exercise.toOffer;

import AlgorithmTraining.G55Utils.Java.*;
import java.util.*;
/**
 * Created by G5501 on 2017/9/13.
 */

class Solution060 {
    String Serialize(TreeNode root) { //使用前序遍历序列化
        StringBuilder sb = new StringBuilder();
        serializeRecursive(sb, root);
        String tmpRes = sb.toString();
        return tmpRes.substring(0, tmpRes.length() - 1);
    }

    private void serializeRecursive(StringBuilder serResult, TreeNode root) {
        if (root == null) {
            serResult.append("#,");
        }
        else {
            serResult.append(Integer.toString(root.val) + ",");
            serializeRecursive(serResult, root.left);
            serializeRecursive(serResult, root.right);
        }
    }

    TreeNode Deserialize(String str) {
        String[] deSerArr = str.split(",");
        int[] cursor = new int[1];
        cursor[0] = 0;
        return deSerializeRecursive(deSerArr, cursor);
    }

    TreeNode deSerializeRecursive(String[] seSerArr, int[] cursor) {
        String nodeValue = seSerArr[cursor[0]];
        if (nodeValue.equals("#")) {
            cursor[0]++;
            return null;
        }
        else {
            TreeNode root = new TreeNode(Integer.parseInt(seSerArr[cursor[0]]));
            cursor[0]++;
            root.left = deSerializeRecursive(seSerArr, cursor);
            root.right = deSerializeRecursive(seSerArr, cursor);
            return root;
        }
    }
}
public class No060 {
    public static void main(String[] args) {
        Solution060 s = new Solution060();
        Integer[] arr = {1, 2, 3, 4, null, null, 5};
        TreeNode root = TreeUtil.deserialize(arr);
        TreeUtil.showTree(root);
        String sered = s.Serialize(root);
        System.out.println(sered);
        TreeNode rootNew = s.Deserialize(sered);
        TreeUtil.showTree(rootNew);
    }
}
