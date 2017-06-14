# Definition for a  binary tree node
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *


class BSTIterator(object):
    """
    O(1) times
    O(n) memory
    """
    def __init__(self, root):
        """
        :type root: TreeNode
        """
        self.storage = []
        self.ptr = 0
        self.midTraverse(root)

    def midTraverse(self, root):
        if root is None:
            return
        else:
            self.midTraverse(root.left)
            self.storage.append(root.val)
            self.midTraverse(root.right)

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.ptr < len(self.storage)

    def next(self):
        """
        :rtype: int
        """
        res = self.storage[self.ptr]
        self.ptr += 1
        return res

        # Your BSTIterator will be called like this:
        # i, v = BSTIterator(root), []
        # while i.hasNext(): v.append(i.next())

if __name__ == "__main__":
    root = TreeUtil.deserialize([1, 2, 3])
    s = BSTIterator(root)
    while s.hasNext():
        print(s.next())
