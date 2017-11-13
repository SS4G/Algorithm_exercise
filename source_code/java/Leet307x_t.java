package AlgorithmTraining.exercise.leetcodeXiaoXiang2;

/**
 * Created by G5501 on 2017/10/18.
 */
class Leet307x {
    public void test() {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        NumArray qr = new NumArray(nums);
        assert qr.sumRange(0, 2) == 3;
        qr.update(1, 2);
        //0, 2, 2, 3, 4, 5, 6, 7, 8, 9
        assert qr.sumRange(0, 2) == 4;
        qr.update(4, 7);
        //0, 2, 2, 3, 7, 5, 6, 7, 8, 9
        assert qr.sumRange(0, 4) == 14;
        qr.update(6, 4);
        //0, 2, 2, 3, 7, 5, 4, 7, 8, 9
        //0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        assert qr.sumRange(3, 7) == 26;
        qr.update(5, 0);
        //0, 2, 2, 3, 7, 0, 4, 7, 8, 9
        //0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        assert qr.sumRange(4, 9) == 35;
    }
}

class NumArray {
    private int[] tree;
    private int[] copyOfNum;
    public NumArray(int[] nums) {
        if (nums.length > 0) {
            copyOfNum = new int[nums.length];
            System.arraycopy(nums, 0, copyOfNum, 0, nums.length);
            int l = 0x01;
            while (l < nums.length)
                l <<= 1;
            tree = new int[2 * l + 100]; // get full binary tree space
            buildSegmentTree(copyOfNum, 0, 0, copyOfNum.length - 1);
        }
    }

    public void buildSegmentTree(int[] nums, int rootIdx, int st, int ed) {
        if (ed - st == 0) {
            tree[rootIdx] = nums[st];
            //System.out.println("rootIdx = " + rootIdx + " st=" + st + " ed=" + ed + " sum=" + tree[rootIdx]);
        }
        else {
            int mid = (st + ed) >> 1;
            buildSegmentTree(nums, (rootIdx << 1) + 1,  st, mid);
            buildSegmentTree(nums, (rootIdx << 1) + 2, mid + 1, ed);
            tree[rootIdx] = tree[(rootIdx << 1) + 1] + tree[(rootIdx << 1) + 2];
            //System.out.println("rootIdx = " + rootIdx + " st=" + st + " ed=" + ed + " sum=" + tree[rootIdx]);
        }
    }

    public void update(int i, int val) {
        updateRecursive(0, i, val, 0, copyOfNum.length - 1);
    }

    public int updateRecursive(int rootIdx, int updateIdx, int val, int st, int ed) {
        int changeVal = 0;
        if (st == ed && updateIdx == st) {
            changeVal = val - tree[rootIdx];
            tree[rootIdx] = val;
            return changeVal;
        }
        else if (updateIdx < st || updateIdx > ed) {
            return 0;
        }
        else {
            int mid = (st + ed) >> 1;
            int leftChange = updateRecursive((rootIdx << 1) + 1, updateIdx, val, st, mid);
            int rightChange = updateRecursive((rootIdx << 1) + 2, updateIdx, val, mid + 1, ed);
            tree[rootIdx] += leftChange == 0 ? rightChange : leftChange;
            return leftChange == 0 ? rightChange : leftChange;
        }
    }

    public int sumRange(int i, int j) {
        return sumRangeRecursive(0, 0, copyOfNum.length - 1, i, j);
    }

    public int sumRangeRecursive(int rootIdx, int leftBound, int rightBound ,int i, int j) {
        //System.out.println("l=" + leftBound + " r=" + rightBound + " root Idx=" + rootIdx);
        if (leftBound >= i && rightBound <= j) {
            //System.out.println("le=" + leftBound + " ri=" + rightBound + " val=" + tree[rootIdx] + " rootIdx=" + rootIdx);
            return tree[rootIdx];
        }
        else if (leftBound > j || rightBound < i) { // out of bound
            return 0;
        }
        else { //一只脚在里面
            int mid = (leftBound + rightBound) >> 1;
            int left = sumRangeRecursive((rootIdx << 1) + 1, leftBound, mid, i, j);
            int right = sumRangeRecursive((rootIdx << 1) + 2, mid + 1, rightBound, i, j);
            return left + right;
        }
    }
}


public class Leet307x_t {
    public static void main(String[] args) {
        Leet307x leet = new Leet307x();
        leet.test();
    }
}
