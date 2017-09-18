# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: void Do not return anything, modify root in-place instead.
        """
        if root is None:
            return
        else:
            self.flattenReturn(root)

    def flattenReturn(self, root):
        assert root is not None, "wtf?"
        if root.left is None and root.right is None:
            return root
        if root.left is not None:
            leftTali = self.flattenReturn(root.left)
        else:
            leftTali = None
        if root.right is not None:
            rightTail = self.flattenReturn(root.right)
        else:
            rightTail = None

        l = root.left
        r = root.right
        if l is not None and r is not None:
            root.right = l
            root.left = None
            leftTali.right = r
            return rightTail
        elif l is None and r is not None:
            root.right = r
            root.left = None
            return rightTail
        elif r is None and l is not None:
            root.right = l
            root.left = None
            return leftTali
        else:
            assert False, "wtf2?"
            return None

if __name__ == "__main__":
    s = Solution()
    arr = []
    root = TreeUtil.deserialize(arr)
    s.flatten(root)
    TreeUtil.showTree(root)






