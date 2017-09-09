from AlgorithmTraining.G55Utils.Py.Utils import *


class Solution(object):
    def addOneRow(self, root, v, d):
        """
        :type root: TreeNode
        :type v: int
        :type d: int
        :rtype: TreeNode
        """
        if d == 1:
            newroot = TreeNode(v)
            newroot.left = root
            return newroot
        else:
            self.traverse(root, d, 1, v)
            return root

    def traverse(self, root, targetDepth, currentDepth, insertValue):
        if root is None:
            return None  # assert False, "can't be here"
        else:
            print(currentDepth)
            if currentDepth == targetDepth - 1:
                leftSub = root.left
                rightSub = root.right
                root.left = TreeNode(insertValue)
                root.right = TreeNode(insertValue)
                root.left.left = leftSub
                root.right.right = rightSub
            else:
                self.traverse(root.left, targetDepth, currentDepth + 1, insertValue)
                self.traverse(root.right, targetDepth, currentDepth + 1, insertValue)

if __name__ == "__main__":
    s = Solution()
    # oriArr = [4, 2, 6, 3, 1, 5]
    oriArr = [4, 2, None, 3, 1]
    oriRoot = TreeUtil.deserialize(oriArr)
    # TreeUtil.showTree(oriRoot)
    newRoot = s.addOneRow(oriRoot, 1, 3)
    print(TreeUtil.serialize(newRoot))
