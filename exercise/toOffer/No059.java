package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/13.
 */

import AlgorithmTraining.G55Utils.Java.*;

import java.util.*;

/**
 * Created by G5501 on 2017/9/13.
 */

class Solution059 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null)
            return new ArrayList<>();
        ArrayList<TreeNode> fifo = new ArrayList<>();
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        fifo.add(pRoot);
        levelMap.put(pRoot, 0);
        int rd = 0;
        int maxlevel = 0;
        while (rd < fifo.size()) {
            TreeNode father = fifo.get(rd);
            rd++;
            if (father.left != null) {
                fifo.add(father.left);
                levelMap.put(father.left, levelMap.get(father) + 1);
                maxlevel = Math.max(maxlevel, levelMap.get(father) + 1);
            }
            if (father.right != null) {
                fifo.add(father.right);
                levelMap.put(father.right, levelMap.get(father) + 1);
                maxlevel = Math.max(maxlevel, levelMap.get(father) + 1);
            }
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < maxlevel + 1; i++) {
            result.add(new ArrayList<>());
        }

        for (TreeNode node : fifo) {
            result.get(levelMap.get(node)).add(node.val);
        }
        for (int i = 0; i < result.size(); i++) {
            if ((i & 0x01) != 0) {
                Collections.reverse(result.get(i));
            }
        }
        return result;
    }
}
public class No059 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeUtil.deserialize(arr);
        Solution059 s = new Solution059();
        System.out.println(s.Print(root));;
    }
}