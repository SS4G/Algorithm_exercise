package AlgorithmTraining.exercise.toOffer;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * Created by G5501 on 2017/9/12.
 */
class AuxClass {
    static int cnt = 0;
    AuxClass next;
    AuxClass (int n) {
        cnt += n;
        boolean dummy = n > 0 && (new AuxClass(n - 1) != null); //使用短路法则 来控制流程
    }
}

class Solution045 {
    public int Sum_Solution(int n) {
        AuxClass x = new AuxClass(n);
        int res = AuxClass.cnt;
        AuxClass.cnt = 0; // 清除状态 因为这个函数可能被多次调用
        return res;
    }
}
public class No045 {
    public static void main(String[] args) {
        Solution045 s = new Solution045();
        int x = s.Sum_Solution(5);
        System.out.println(x);
    }
}
