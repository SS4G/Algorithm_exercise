## 236. Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

```
.
        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
```

For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


#### tips
使用先序遍历 来get到两个要找的点 并记录下他们的路径 然后从头查找公共的部分
需要注意的是 这个题目中区别点用的是对象的指针 而不是节点中的值 注意看参数说明

#### mycode


```
class Solution(object):
    def lowestCommonAncestor(self, root, p, q):
        """
        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        if root is None:
            return None
        stack = []
        pathRecord = []
        self.preOrderTraverse(root, [p, q], stack, pathRecord)
        #print(pathRecord[p.val])
        #print(pathRecord[q.val])
        node = None
        i = pathRecord[0]
        j = pathRecord[1]
        for k in range(min(len(i), len(j))):
            if i[k] is not j[k]:
                node = i[k - 1]
                return node
            else:
                node = i[k]
        return node

    def preOrderTraverse(self, root, target, stack, pathRecord):
        if root is None:
            return
        else:
            stack.append(root)
            if root is target[0] or root is target[1]:
                pathRecord.append(stack[:])
            self.preOrderTraverse(root.left, target, stack, pathRecord)
            self.preOrderTraverse(root.right, target, stack, pathRecord)
            stack.pop()
```

