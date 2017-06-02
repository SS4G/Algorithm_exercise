# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

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
            output.append("n")
        else:
            output.append(str(root.val))
            self.dfsSerialize(root.left,output)
            self.dfsSerialize(root.right,output)

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        nodeMark = [0, ]
        return self.dfsDeserialize(data, nodeMark)

    def dfsDeserialize(self, nodes, nodeMark):
        if nodes[nodeMark[0]] == "n":
            nodeMark[0] += 1
            return None
        else:

            root = TreeNode(int(nodes[nodeMark[0]]))
            nodeMark[0] += 1
            root.left = self.dfsDeserialize(nodes, nodeMark)
            root.right = self.dfsDeserialize(nodes, nodeMark)
            return root

        # Your Codec object will be instantiated and called as such:
        # codec = Codec()
        # codec.deserialize(codec.serialize(root))

if __name__ == "__main__":
    c = Codec()
    a0 = TreeNode(0)
    a1 = TreeNode(1)
    a2 = TreeNode(2)
    a3 = TreeNode(3)
    a4 = TreeNode(4)
    a5 = TreeNode(5)
    a6 = TreeNode(6)
    a7 = TreeNode(7)
    a0.left = a1
    a0.right = a2
    a1.left = a3
    a1.right = a4
    a2.left = a5
    a2.right = a6
    a3.left = a7
    res = c.serialize(a0)
    print(res)
    c.deserialize(res)