package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.*;
import java.util.*;

/**
 * Created by G5501 on 2017/10/14.
 */
class TupleElement2<TA, TB> {
    public TA r0;
    public TB r1;
    TupleElement2(TA ta, TB tb) {
        r0 = ta;
        r1 = tb;
    }
}

class Leet199x {
    public List<Integer> rightSideView(TreeNode root) {
        List<TupleElement<TreeNode, Integer>> list = new ArrayList<>();
        list.add(new TupleElement<>(root, 0));
        int rd = 0;
        while (rd < list.size()) {
            TreeNode last = list.get(rd).r0;
            if (last.left != null) {
                list.add(new TupleElement<>(last.left, list.get(rd).r1 + 1));
            }

            if (last.right != null) {
                list.add(new TupleElement<>(last.right, list.get(rd).r1 + 1));
            }
            rd++;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1 && (list.get(i + 1).r1 != list.get(i).r1)) {
                res.add(list.get(i).r0.val);
            }
            else if (i == list.size() - 1) {
                res.add(list.get(i).r0.val);
            }
        }
        System.out.println(res);
        return res;
    }
}

public class Leet199x_t {
    public static void main(String[] args) {
        Leet199x leet = new Leet199x();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode root = TreeUtil.deserialize(arr);
        System.out.println(leet.rightSideView(root));
    }
}
