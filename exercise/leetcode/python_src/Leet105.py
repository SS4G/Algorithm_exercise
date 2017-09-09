# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from AlgorithmTraining.G55Utils.Py.Utils import *

class Solution(object):
    def buildTree(self, preorder, inorder):
        """
        :type preorder: List[int]
        :type inorder: List[int]
        :rtype: TreeNode
        """
        if len(inorder) == 1:
            return TreeNode(inorder[0])
        elif len(inorder) == 0:
            return None

        root = TreeNode(preorder[0])
        inorderRootIndex = inorder.index(preorder[0])
        leftTreeIn = inorder[:inorderRootIndex]
        rightTreeIn = inorder[inorderRootIndex+1:]
        leftTreePre = preorder[1:len(leftTreeIn)+1]
        rightTreePre = preorder[len(leftTreeIn)+1:]
        root.left = self.buildTree(leftTreePre, leftTreeIn)
        root.right = self.buildTree(rightTreePre, rightTreeIn)
        return root

if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3, 4, 5, 6, 7]
    root = TreeUtil.deserialize(arr)
    preorder = TreeUtil.toPreOrder(root, [])
    inorder = TreeUtil.toInOrder(root, [])
    # postorder = TreeUtil.toPostOrder(root, [])
    #inorder = [4, 2, 5, 1, 6, 3, 7]
    #postorder = [4, 5, 2, 6, 7, 3, 1]
    root = s.buildTree(preorder, inorder)
    print(TreeUtil.serialize(root))
