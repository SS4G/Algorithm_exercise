# leetcdoe 226
## Question
#### Invert Binary Tree
Invert a binary tree.


```
.....4
   /   \
  2     7
 / \   / \
1   3 6   9

to

     4
   /   \
  7     2
 / \   / \
9   6 3   1
```
## Answer
翻转二叉树这个问题本身对翻转的定义就是递归的
即 对于任意一个节点，其左子树和右子树的根节点相对于以前是反的
这种问题最好是用递归求解

##### code (beat 70% Python)
```

class Solution(object):
    def invertTree(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        if root!=None:
            if root.left!=None or root.right!=None:
                root.left,root.right=root.right,root.left
                self.invertTree(root.left)
                self.invertTree(root.right)
        return root
```
