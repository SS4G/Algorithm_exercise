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