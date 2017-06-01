#Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


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
