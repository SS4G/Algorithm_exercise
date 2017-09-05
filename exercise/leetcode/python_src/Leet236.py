# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def lowestCommonAncestor(self, root, p, q):
        """
        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        if root is None:
            return None
        stack = []
        pathRecord = []
        self.preOrderTraverse(root, [p, q], stack, pathRecord)
        #print(pathRecord[p.val])
        #print(pathRecord[q.val])
        node = None
        i = pathRecord[0]
        j = pathRecord[1]
        for k in range(min(len(i), len(j))):
            if i[k] is not j[k]:
                node = i[k - 1]
                return node
            else:
                node = i[k]
        return node

    def preOrderTraverse(self, root, target, stack, pathRecord):
        if root is None:
            return
        else:
            stack.append(root)
            if root is target[0] or root is target[1]:
                pathRecord.append(stack[:])
            self.preOrderTraverse(root.left, target, stack, pathRecord)
            self.preOrderTraverse(root.right, target, stack, pathRecord)
            stack.pop()

if __name__ == "__main__":
    s = Solution()
    #arr = [1, 2]
    arr = [37, -34, -48, None, -100, -100, 48, None, None, None, None, -54, None, -71, -22, None, None, None, 8]
    root = TreeUtil.deserialize(arr)
    # TreeUtil.showTree(root)
    a0 = root.left.right
    a1 = root.right.left
    b = root.right.right.left.left
    n = s.lowestCommonAncestor(root, a0, b)
    print(n.val)