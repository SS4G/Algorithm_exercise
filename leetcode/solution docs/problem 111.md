## 111. Minimum Depth of Binary Tree   
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

#### tips
注意当一个节点 只有左子树或者只有右子树的时候，不可以认为以这个节点为根节点的子树的高度为1 因为现在这个节点的一个子树是根本不存在而不是最小深度为0

```
-  1
  / 
 2 
 min_depth=2  
 rather than 1
```
##### my_code
```
class Solution(object):
    def minDepth(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root==None:
            return 0
        left_len =self.minDepth(root.left)
        right_len=self.minDepth(root.right)
        return left_len+right_len+1 if (left_len==0 or right_len==0) else min(left_len,right_len)+1
```
