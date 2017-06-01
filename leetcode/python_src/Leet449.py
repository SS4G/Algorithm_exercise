# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Codec:
    """
    using DFS 所有节点都有左右子树的 二叉树 先序遍历的结果是一定的
    """
    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """
        output = []
        self.dfsSerialize(root, output)
        return output

    def dfsSerialize(self, root, output):
        if root is None:
            output.append("null")
        else:
            output.append(str(root.val))

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """


        # Your Codec object will be instantiated and called as such:
        # codec = Codec()
        # codec.deserialize(codec.serialize(root))