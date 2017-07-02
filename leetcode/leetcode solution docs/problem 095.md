## 95. Unique Binary Search Trees II

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

```
。
   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

#### tips
这个继承了 leet096 这个是要把对应的 二叉树输出来 还是动态规划  但是具体的就是及录中存放的是s(n)对应的所有结构不同的树

中间需要写一个copy树的操作函数 

对于最终的结果 只是有了树的结构但是没有树的值 所以写一个渲染函数 将值按照中序遍历 将值填充到树中


#### mycode
```
class Solution(object):
    def generateTrees(self, n):
        """
        :type n: int
        :rtype: List[TreeNode]
        """
        if n == 0:
            return []

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
