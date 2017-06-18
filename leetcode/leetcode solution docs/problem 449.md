## 449. Serialize and Deserialize BST

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

#### tips
对二叉树的序列化和反序列化 有两种方法 一种是使用队列的BFS方法 即层次化的遍历二叉树
另一种是 DFS 使用先序遍历 来序列化二叉树， 但是这种先序遍历需要将 空指针 序列化为None 进行存储 这样才能 通过先序遍历来恢复

此处讲一下先序遍历的 反序列化
如 和适合使用递归来进行反序列化 需要使用一个游标来记录当前已经将哪个节点添加在了 树上

相关题目见 Leet297 Leet331

```
2 9 # # 7 # # ->
  2
 / \
9   7
```

#### mycode
```Python
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
```
