## 572. Subtree of Another Tree
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.


```
Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
```

Return true, because t has the same structure and node values with a subtree of s.

```
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
```

#### tips
一边先序遍历 一边以当前的遍历点为根部来将t 进行比较
重点在于用于比较两颗树是否相同的那个递归方法

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
    boolean isSameTree(TreeNode root0, TreeNode root1) {
        if (root0 == null && root1 == null)
            return true;
        else if (root0 == null || root1 == null)
            return false;
        if (root0.val == root1.val) {
            return isSameTree(root0.left, root1.left) && isSameTree(root0.right, root1.right);
        }
        else
            return false;
    }
    boolean preOrderCompare(TreeNode mainTree, TreeNode t) {     
        if (mainTree == null)
            return false;
        if (isSameTree(mainTree, t))
            return true;
        else
            return preOrderCompare(mainTree.left, t) || preOrderCompare(mainTree.right, t);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return preOrderCompare(s,t);
    }
}
```
