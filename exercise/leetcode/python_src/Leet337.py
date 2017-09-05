# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def rob(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        return max(self.robRecursive(root))

    def robRecursive(self, root):
        if root is None:
            return 0, 0  # rob, not rob
        leftYes, leftNo = self.robRecursive(root.left)
        rightYes, rightNo = self.robRecursive(root.right)
        rootYes = leftNo + rightNo + root.val
        rootNo = max(leftYes, leftNo) + max(rightNo, rightYes)
        return rootYes, rootNo

if __name__ == "__main__":
    s = Solution()
    data = [3, 2, 3, None, 3, None, 1]
    data = [3, 4, 5, 1, 3, None, 1]
    root = TreeUtil.deserialize(data)
    print(s.rob(root))