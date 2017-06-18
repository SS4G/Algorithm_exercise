## 116. Populating Next Right Pointers in Each Node

Given a binary tree


```
struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
```

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,

```
.
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
```

After calling your function, the tree should look like:

```
.
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
```

#### tips
可以用递归来记录上一层的根节点 使用先序遍历 从上到下 进行 下层在构建next指针时 在先序遍历的情况下，上层已经完成了 next的构建 具体实现见代码 这种方法仅在树是满二叉树的情形下有用

#### mycode


```
class TreeLinkNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        self.next = None


class Solution:
    # @param root, a tree link node
    # @return nothing
    def connect(self, root):
        self.preOrderTraverse(root, None, False)

    def preOrderTraverse(self, root, lastRoot, isLeft):
        if root is None:
            return
        self.linkToRight(root, lastRoot, isLeft)
        self.preOrderTraverse(root.left, root, True)
        self.preOrderTraverse(root.right, root, False)

    def linkToRight(self, root, lastRoot, isLeft):
        if root is None:
            return
        else:
            if lastRoot is not None:  # non-root node
                if isLeft:
                    root.next = lastRoot.right
                else:
                    if lastRoot.next is not None:
                        root.next = lastRoot.next.left
```
