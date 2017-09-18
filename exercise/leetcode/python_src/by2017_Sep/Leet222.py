# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def countNodes(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root is None:
            return 0
        hl = 0
        hr = 0
        tmpl = root
        while tmpl is not None:
            tmpl = tmpl.left
            hl += 1
        tmpr = root
        while tmpr is not None:
            tmpr = tmpr.right
            hr += 1
        if hl == hr:
            return 2**hl - 1
        else:
            return self.countNodes(root.left) + self.countNodes(root.right) + 1

if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3, 4]
    t = TreeUtil.deserialize(arr)
    print(s.countNodes(t))


