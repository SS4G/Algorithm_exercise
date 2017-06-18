## 222. Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
#### tips
这个对于完全二叉树如果直接用层次遍历去数会超时 所以考虑用高度来实现这个问题
求一个完全二叉树的最左边的高度和最右边的高度 看两者是否相同 如果相同说明他是一个满二叉树 可以用公示区求其节点数 如果不满足 就用递归去求解  
具体见代码

#### mycode
```
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
```
