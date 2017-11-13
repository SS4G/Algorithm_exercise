## 96. Unique Binary Search Trees

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

```
。
   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```
#### tips
这个题目也是动态规划 注意说的是结构上的不同 不是值分布的不同 因为一旦亦可二叉树的节点的数量是确定的以后 而且是亦可二叉搜索树 所以他的值分布必然是按照中序遍历填充上去的


s(n) = sum(s(i)*s(n-1-i) for i in range(n - 1))
程序上可以根据上式的对称性用×2的方法来加速

原理如下
假设当前是 4个节点 且已知 0,1,2,3 节点的组合数量

那么 可以考虑如下的情况 左边0个 右边3个 s(0)*s(3) 左边1个 右边2个 s(1)*s(2)。。。。
以此类推 就是一个排列组合的问题

#### mycode

```
class Solution(object):
    def generateTrees(self, n):
        """
        :type n: int
        :rtype: List[TreeNode]
        """
        if n == 0:
            return [None, ]

        record = [None, ] * (n + 1)
        record[0] = [None, ]
        record[1] = [TreeNode(1), ]
        for i in range(2, n + 1):
            record[i] = [] 
            for j in range(i):
                leftTrees = record[j]
                rightTrees = record[i - 1 - j]
                for l in leftTrees:
                    for r in rightTrees:
                        root = TreeNode(0)
                        root.left = self.copyTree(l)
                        root.right = self.copyTree(r)
                        record[i].append(root)
        for root in record[n]:
            self.renderTree(root)            
        return record[n]
        
    def copyTree(self, root):
        return self.copyTreeRecursive(root)
        
    def copyTreeRecursive(self, root):
        if root is None:
            return None
        else:
            rootCopy = TreeNode(root.val)
            rootCopy.left = self.copyTreeRecursive(root.left)
            rootCopy.right = self.copyTreeRecursive(root.right)
            return rootCopy
        
    def renderTree(self, root):
        lastMark = [0, ]
        self.renderRecursive(root, lastMark)
        
    def renderRecursive(self, root, lastMark):
        if root is None:
            return 
        self.renderRecursive(root.left, lastMark)
        root.val = lastMark[0] + 1
        lastMark[0] += 1
        self.renderRecursive(root.right, lastMark)
```
