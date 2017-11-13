## 129. Sum Root to Leaf Numbers

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,


```
.   1
   / \
  2   3
```

The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

#### tips
使用一个堆栈来跟踪当前的路径 当遇到一个叶子节点时 将路径保存为结果 然后对结果进行简单的处理即可

#### mycode

```
class Solution(object):
    def sumNumbers(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        stack = []
        output = []
        if root is None:
            return 0
        self.preOrderTraverse(root, stack, output)
        if len(output) == 0:
            return 0
        return sum([int(i) for i in output])

    def preOrderTraverse(self, root, stack, output):
        stack.append(str(root.val))
        if root.left is None and root.right is None:
            output.append("".join(stack))
            stack.pop()
            return
        if root.left is not None:
            self.preOrderTraverse(root.left, stack, output)
        if root.right is not None:
            self.preOrderTraverse(root.right, stack, output)
        stack.pop()
```
