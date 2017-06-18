## 173. Binary Search Tree Iterator
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
#### tips
根据题目的要求， 是要求空间复杂度为O(h) h为树的高度
所以 用一个堆栈来跟中到最小节点的路径 
每次迭代一次后 就弹出最小的节点 如果这个节点有右子树 ， 那么就把弹出该节点后的堆栈继续向右子树的的最小点推进 如果没有右子树 那么直接弹出即可
#### mycode
```
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
```
