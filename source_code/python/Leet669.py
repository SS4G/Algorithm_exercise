from AlgorithmTraining.G55Utils.Py.Utils import *
class Solution:
    def trimBST(self, root, L, R):
        """
        :type root: TreeNode
        :type L: int
        :type R: int
        :rtype: TreeNode
        """
        if root is None:
            return None
        elif root.val > R:
            return self.trimBST(root.left, L, R)
        elif root.val < L:
            return self.trimBST(root.right, L, R)
        else:
            root.left = self.trimBST(root.left, L, R)
            root.right = self.trimBST(root.right, L, R)
            return root

if __name__ == "__main__":
    s = Solution()
    arr = [5, 2, 7, 1, 3, 6, 8]
    root = TreeUtil.deserialize(arr)
    TreeUtil.showTree(s.trimBST(root, 3, 6))



