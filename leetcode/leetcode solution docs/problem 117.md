## 117. Populating Next Right Pointers in Each Node II

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,

```
.
         1
       /  \
      2    3
     / \    \
    4   5    7
```

After calling your function, the tree should look like:

```
.
        1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
```

#### tips
使用BFS 然后用几个变量来记录 要操作的一系列节点 
curLineStart 当前正在访问的行的首个节点
lineRoot 当前正在访问的行的节点

nextLineStart 正在构建的行的节点
nextLineAdd 正在构建的行的节点的插入位置



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
        if root is None:
            return None
        else:
            curLineStart = root
            nextLineStart = None
            while curLineStart is not None:
                lineRoot = curLineStart
                while lineRoot is not None:
                    buffer = []
                    #  将可能存在的下一个节点添加进队列中
                    if lineRoot.left is not None:
                        buffer.append(lineRoot.left)
                    if lineRoot.right is not None:
                        buffer.append(lineRoot.right)
                    if nextLineStart is None:  # 下一排的头部节点还没有构建
                        if len(buffer) == 2:
                            nextLineStart = buffer[0]
                            buffer[0].next = buffer[1]
                            nextLineAdd = buffer[1]
                        elif len(buffer) == 1:
                            nextLineStart = buffer[0]
                            nextLineAdd = buffer[0]
                    else:
                        if len(buffer) == 2:
                            nextLineAdd.next = buffer[0]
                            buffer[0].next = buffer[1]
                            nextLineAdd = buffer[1]
                        elif len(buffer) == 1:
                            nextLineAdd.next = buffer[0]
                            nextLineAdd = buffer[0]
                    lineRoot = lineRoot.next
                curLineStart = nextLineStart
                nextLineStart = None
```
