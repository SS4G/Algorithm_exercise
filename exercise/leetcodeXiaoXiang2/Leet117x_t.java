package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

import AlgorithmTraining.G55Utils.Java.TreeNode;
import java.util.*;
/**
 * Created by G5501 on 2017/10/12.
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left = null, right = null, next = null;
    TreeLinkNode(int x) {
        val = x;
    }
}

class TupleElement<TA, TB> {
    public TA r0;
    public TB r1;
    TupleElement(TA ta, TB tb) {
        r0 = ta;
        r1 = tb;
    }
}

class Leet117x {
    public void connect(TreeLinkNode root) {
        List<TupleElement<TreeLinkNode, Integer>> fifo = new ArrayList<>();
        int wr = 0;
        int rd = 0;
        if (root != null) {
            fifo.add(new TupleElement<>(root, 0));
            wr = 1;
            while (rd < wr) {
                TupleElement<TreeLinkNode, Integer> tmpRootElement = fifo.get(rd);
                if (tmpRootElement.r0.left != null) {
                    fifo.add(new TupleElement<>(tmpRootElement.r0.left, tmpRootElement.r1 + 1));
                    wr++;
                }
                if (tmpRootElement.r0.right != null) {
                    fifo.add(new TupleElement<>(tmpRootElement.r0.right, tmpRootElement.r1 + 1));
                    wr++;
                }
                rd++;
            }
        }

        for (int i = 0; i < fifo.size() - 1; i++) {
            if (fifo.get(i).r1.equals(fifo.get(i + 1).r1)) {
                fifo.get(i).r0.next = fifo.get(i + 1).r0;
            }
        }
    }
}

public class Leet117x_t {
    public static void main(String[] args) {

    }
}
