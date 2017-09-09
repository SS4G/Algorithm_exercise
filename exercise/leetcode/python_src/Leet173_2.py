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
    O(lgn) memory  height = lgn
    """
    def __init__(self, root):
        """
        :type root: TreeNode
        """
        self.storage = []
        self.ptr = 0
        self.currentStack = []
        self.fillStack(root)

    def fillStack(self, root):
        while root is not None:
            self.currentStack.append(root)
            root = root.left

    def hasNext(self):
        """
        :rtype: bool
        """
        return len(self.currentStack) > 0

    def next(self):
        """
        :rtype: int
        """
        res = self.currentStack[-1].val
        rightTree = None
        if self.currentStack[-1].right is not None:
            rightTree = self.currentStack[-1].right
        self.currentStack.pop()
        if rightTree is not None:
            self.fillStack(rightTree)
        return res

        # Your BSTIterator will be called like this:
        # i, v = BSTIterator(root), []
        # while i.hasNext(): v.append(i.next())

if __name__ == "__main__":
    root = TreeUtil.deserialize([2, 1, 3])
    s = BSTIterator(root)
    while s.hasNext():
        print(s.next())
