## 98. Validate Binary Search Tree Add to List

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

```
Example 1:
    2
   / \
  1   3
```

Binary tree [2,1,3], return true.

```
Example 2:
    1
   / \
  2   3
```

Binary tree [1,2,3], return false.

#### tips
使用中序遍历 后 检查遍历序列是否是有序的

#### bug warning
不可以使用递归 虽然左右子树都是BST 而且 root.left.val < root.val < root.right.val
仍然不能保证这棵树是BST

#### mycode
extends 操作会更快
```
# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        order = self.midTraverse(root)
        if len(order) <= 1:
            return True
        else:
            for i in range(1, len(order)):
                if order[i] >= order[i-1]:
                    return False
            return True



    def midTraverse(self, root):
        if root is None:
            return []
        res = []
        res.extend(self.midTraverse(root.left))
        res.append(root.val)
        res.extend(self.midTraverse(root.right))
        return res
```
