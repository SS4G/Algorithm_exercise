## 297. Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree


```
1
   / \
  2   3
     / \
    4   5
```

as "[1,2,3,null,null,4,5]", just the same as 
[how LeetCode OJ serializes a binary tree.](https://leetcode.com/faq/#binary-tree)
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
#### tips
使用BFS （层次化遍历二叉树） 来完成
相关题目见 DFS序列环 二叉树

#### mycode
```Python
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
```
