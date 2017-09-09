# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def checkEqualTree(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        totalSum = self.traversRootSum(root)
        if totalSum & 0x01 == 0:
            return self.traverse2nd(root, totalSum >> 1)[1]
        else:
            return False

    def traversRootSum(self, root):
        if root is not None:
            leftSum = self.traversRootSum(root.leftSum)
            rightSum = self.traversRootSum(root.rightSum)
            return root.val + leftSum + rightSum
        else:
            return 0

    def traverse2nd(self, root, halfSum):
        if root is not None:
            leftSum, ls = self.traverse2nd(root.leftSum, halfSum)
            rightSum, rs = self.traverse2nd(root.rightSum, halfSum)
            if ls or rs:
                return 0, True
            elif root.val + leftSum + rightSum == halfSum:
                return 0, True
            else:
                return root.val + leftSum + rightSum, False
        else:
            return 0, False

