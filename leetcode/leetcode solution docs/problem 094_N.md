# leetcdoe 94
## Question 
## hasn't been finished
#### Binary Tree Inorder Traversal
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],

```
....1
    \
     2
    /
   3
```

return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
## Answer
中序遍历 没什么好说的

##### code(beat %90 Python)

```
class Solution(object):
    def __init__(self):
        self.res=[]
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root!=None:
            self.inorderTraversal(root.left)
            self.res.append(root.val)
            self.inorderTraversal(root.right)
            
        return self.res
```
