## 106. Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

#### tips
使用递归
 使用python内置的index函数更高效一些
 如果用索引代替数组切片可能会更快
 
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
    def buildTree(self, inorder, postorder):
        """
        :type inorder: List[int]
        :type postorder: List[int]
        :rtype: TreeNode
        """
        if len(inorder) == 1:
            return TreeNode(inorder[0])
        elif len(inorder) == 0:
            return None

        root = TreeNode(postorder[-1])
        inorderRootIndex = inorder.index(postorder[-1])
        leftTreeIn = inorder[:inorderRootIndex]
        rightTreeIn = inorder[inorderRootIndex+1:]
        leftTreePost = postorder[:len(leftTreeIn)]
        rightTreePost = postorder[len(leftTreeIn):len(postorder)-1]
        root.left = self.buildTree(leftTreeIn, leftTreePost)
        root.right = self.buildTree(rightTreeIn, rightTreePost)
        return root

if __name__ == "__main__":
    s = Solution()
    arr = [1, 2, 3, 4, None, 6]
    root = TreeUtil.deserialize(arr)
    preorder = TreeUtil.toPreOrder(root, [])
    inorder = TreeUtil.toInOrder(root, [])
    postorder = TreeUtil.toPostOrder(root, [])
    #inorder = [4, 2, 5, 1, 6, 3, 7]
    #postorder = [4, 5, 2, 6, 7, 3, 1]
    root = s.buildTree(inorder, postorder)
    print(TreeUtil.serialize(root))
```
