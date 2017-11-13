## 563. Binary Tree Tilt
Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.


```
Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
```

Note:

The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.

#### tips
递归来解决吧
#### mycode

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private static final int SUM = 1;
    private static final int TILT = 0;

    public static int findTilt(TreeNode root) {
        return findTiltAndSum(root)[TILT];
    }
    public static int[] findTiltAndSum(TreeNode root) {
        int[] result = new int[2];
        if (root == null) {
            result[TILT] = 0;
            result[SUM] = 0;
            return result;
        }
        int[] leftResult = findTiltAndSum(root.left);
        int[] rightResult = findTiltAndSum(root.right);
        result[TILT] = leftResult[TILT] + rightResult[TILT] +
                Math.abs(leftResult[SUM] - rightResult[SUM]);
        result[SUM] = leftResult[SUM] + rightResult[SUM] + root.val;
        return result;
    }
}
```
