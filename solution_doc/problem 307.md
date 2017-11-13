## 307. Range Sum Query - Mutable

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
- The array is only modifiable by the update function.
- You may assume the number of calls to update and sumRange function is distributed evenly.
- 


#### tips
由于这个题目在中间过程中会不断地更新数组中的数据 所以直接的查找 或者一般的dp并不合适 应该使用线段树

线段树本质上是一种区间查找 
比如 0, 1, 2, 3, 4, 5, 7
构造成线段树大概如下
- [0:8]
- [0:3] [4:7]
- [0:1] [2:3] [4:5] [6:7]
- [0:0] [1:1] [2:2] [3:3] [4:4] [5:5] [6:6] [7:7]
- 

全部都是左右闭区间
例如要查找[3:5]
查找过程如下
[0:7]
----left Recure
[0:3] 
[2:3] 
[3:3]

----right recure
[4:7]
[4:5]

最后[3:3] + [4:5]
所有的求和都是递归的线上的传递
所以所有的递归函数的含义是 
求 目标区间与当前区间重合部分的和
即目标区间[i:j] 与 [leftBound:rightBound] 重合的和
然后将这些部分结果地柜的向上传递 然后合并成最终结果

关于update 的方法 也是先向下地柜查找 然后向上递归的回溯修改

可以参考博文

[线段树学习](http://www.cnblogs.com/TenosDoIt/p/3453089.html)


最后线段树是完满二叉树 所以可以用数组来表示 这个空间与叶子节点n 为O(n)关系 终结点数2n-1 可以数学归纳证明
#### mycode

```Java
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
```
