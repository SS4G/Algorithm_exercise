# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Codec:
    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """

        if root is None:
            return ["null", ]
        stack = []
        output = []
        stack.append(root)
        output.append(str(root.val))
        serializedPtr = 0
        while serializedPtr < len(stack):
            if stack[serializedPtr].left is not None:
                stack.append(stack[serializedPtr].left)
                output.append(str(stack[serializedPtr].left.val))
            else:
                output.append("null")

            if stack[serializedPtr].right is not None:
                stack.append(stack[serializedPtr].right)
                output.append(str(stack[serializedPtr].right.val))
            else:
                output.append("null")
            serializedPtr += 1

        return output

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        if len(data) == 0:
            return None
        datas = [int(i) if i != "null" else None for i in data]

        construct = []
        currentPtr = None
        LeftFlag = True
        for i in range(len(datas)):
            if i == 0:
                if datas[i] is None:
                    return None
                else:
                    construct.append(TreeNode(datas[i]))
                    currentPtr = 0
                    leftFlag = True
            else:
                if datas[i] is not None:
                    if leftFlag:
                        construct[currentPtr].left = TreeNode(datas[i])
                        construct.append(construct[currentPtr].left)
                    else:
                        construct[currentPtr].right = TreeNode(datas[i])
                        construct.append(construct[currentPtr].right)
                leftFlag = not leftFlag
                if leftFlag is True:
                    currentPtr += 1
        return construct[0]
        # Your Codec object will be instantiated and called as such:
        # codec = Codec()
        # codec.deserialize(codec.serialize(root))

        # Your Codec object will be instantiated and called as such:
        # codec = Codec()
        # codec.deserialize(codec.serialize(root))

def showTree(root, layer):
    if root is None:
        # print(" "*layer+"*")
        return
    print("...."*layer+str(root.val))
    showTree(root.left, layer+1)
    showTree(root.right, layer+1)
if __name__ == "__main__":
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

    # showTree(a0, 0)
    c = Codec()
    root = c.deserialize("-1,0,1")
    showTree(root, 0)