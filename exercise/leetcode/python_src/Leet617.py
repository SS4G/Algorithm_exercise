# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def mergeTrees(self, t1, t2):
        """
        :type t1: TreeNode
        :type t2: TreeNode
        :rtype: TreeNode
        """
        return self.preOrderTraverse(t1, t2, True)

    def preOrderTraverse(self, t1, t2, firstInvoke=False):
        # merge t2 to t1
        if not firstInvoke:
            if t1 is None and t2 is None:
                return
            elif t1 is not None and t2 is not None:
                t1.val += t2.val
                print("A")
            else:
                assert False, "can't reach"
        else:
            if t1 is None and t2 is None:
                return
            elif t1 is not None and t2 is not None:
                print("AA")
                t1.val += t2.val
            elif t1 is None and t2 is not None:
                return t2
            else:
                return t1

        if t1.left is None and t2.left is None:
            pass
        elif t1.left is None and t2.left is not None:
            t1.left = t2.left
        elif t1.left is not None and t2.left is None:
            pass
        else:
            self.preOrderTraverse(t1.left, t2.left)

        if t1.right is None and t2.right is None:
            pass
        elif t1.right is None and t2.right is not None:
            t1.right = t2.right
        elif t1.right is not None and t2.right is None:
            pass
        else:
            self.preOrderTraverse(t1.right, t2.right)

        return t1

if __name__ == "__main__":
    s = Solution()
    t1 = [1, 3, 2, 5]
    t2 = [2, 1, 3, None, 4, None, 7]
    t1 = TreeUtil.deserialize(t1)
    t2 = TreeUtil.deserialize(t2)
    TreeUtil.showTree(t1)
    TreeUtil.showTree(t2)
    print(TreeUtil.serialize(s.mergeTrees(t1, t2)))
    TreeUtil.showTree(t1)