## 515. Find Largest Value in Each Tree Row

You need to find the largest value in each row of a binary tree.

Example:
Input: 


```
-.........1
         / \
        3   2
       / \   \  
      5   3   9
```
Output: [1, 3, 9]

#### tips
使用BFS 记录节点何其所在的row 就行

#### mycode

```
# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def largestValues(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []

        nodes = [(root, 0), ]
        ptr = 0
        while ptr < len(nodes):
            row = nodes[ptr][1]
            if nodes[ptr][0].left is not None:
                nodes.append((nodes[ptr][0].left, row + 1))
            if nodes[ptr][0].right is not None:
                nodes.append((nodes[ptr][0].right, row + 1))
            ptr += 1
        print([n[0].val for n in nodes])
        res = []
        lastRow = -1
        for i in nodes:
            if lastRow != i[1]:
                if lastRow != -1:
                    res.append(maxThisRow)
                maxThisRow = i[0].val
                lastRow = i[1]
            else:
                maxThisRow = max(maxThisRow, i[0].val)
        res.append(maxThisRow)
        return res
```
