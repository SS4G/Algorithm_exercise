## 112. Path Sum  
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.


```
For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
```
##### mycode
使用递归，注意考虑空树的情形

```
class Solution(object):
    def hasPathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: bool
        """
        if root==None:#special tree
            return False

        if self.is_leaf(root) and root.val==sum:
            return True
        elif self.is_leaf(root) and root.val!=sum:
            return False
        else:
            return self.hasPathSum(root.left,sum-root.val) or self.hasPathSum(root.right,sum-root.val)


    def is_leaf(self,root):
        return (root.left==None) and (root.right==None)
```
