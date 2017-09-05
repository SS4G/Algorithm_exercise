# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

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
        return left_len+right_len+1 if (left_len==0 or right_len==0) else min(left_len,right_len)

