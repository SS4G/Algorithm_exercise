class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def convertBST(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        previousValue = [0, ]
        self.midTraverse(root, previousValue)
        return root

    def midTraverse(self, root, val):
        if root is None:
            return
        self.midTraverse(root.right, val)
        root.val += val[0]
        val[0] = root.val
        self.midTraverse(root.left, val)
        return


# build and show Tree
class TreeUtil:
    @staticmethod
    def serialize(root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """

        if root is None:
            return ["null", ]
        stack = []
        output = []
        stack.append(root)
        output.append(root.val)
        serializedPtr = 0
        while serializedPtr < len(stack):
            if stack[serializedPtr].left is not None:
                stack.append(stack[serializedPtr].left)
                output.append(stack[serializedPtr].left.val)
            else:
                output.append(None)

            if stack[serializedPtr].right is not None:
                stack.append(stack[serializedPtr].right)
                output.append(stack[serializedPtr].right.val)
            else:
                output.append(None)
            serializedPtr += 1

        return output

    @staticmethod
    def deserialize(data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        if len(data) == 0:
            return None
        datas = [i if i is not None else None for i in data]

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

if __name__ == "__main__":
    s = Solution()
    treedata = [5, 3, 8, 2, 4, 7, 9]
    root = TreeUtil.deserialize(treedata)
    print(TreeUtil.serialize(s.convertBST(root)))
    [29, 36, 17, 38, 33, 24, 9]