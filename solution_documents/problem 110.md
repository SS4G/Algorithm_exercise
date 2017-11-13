# leetcdoe 110
## Question 
#### Balanced Binary Tree
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

## Answer
要找到一种好的递归方法 是指能够将每一个节点的左右子树的深度以及是否是 平衡二叉树的情况返回 注意返回深度时要求不能重复的将每一个节点的深度都调用 深度函数来求一遍这样会导致效率及其的低下 最坏的情况下 与二叉树深度 n 呈 O(n^3)
会TLE 所以应该将每次递归后 除了判断子树是否是平衡二叉树外 还应该将深度结果返回以供上层函数叠加 这一点可以利用Python返回元组的方法来做到

##### code (beat 92% Python) 
```
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def isBalanced(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        return self.res(root)[1]
        
        
        
    def res(self,root):
        if root==None:
            return (0,True)
    
        if root.left!=None and root.right!=None:
            (l_height,l_isbalance)=self.res(root.left)
            (r_height,r_isbalance)=self.res(root.right)
            if l_isbalance and r_isbalance:
                if (l_height-r_height)==0 or (l_height-r_height)==-1 or (l_height-r_height)==1:
                    return (max(l_height,r_height)+1,True)
                else :
                    return (0,False)
            else :
                return (0,False)
        elif root.left==None and root.right==None:
            return (1,True)
        elif root.left==None:
            if self.maxDepth(root.right)>=1:
                return (0,False)
            else:
                return (2,True)
        elif root.right==None:
            if self.maxDepth(root.left)>=1:
                return (0,False)
            else:
                return (2,True)
                        
    
    def maxDepth(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root!=None:
            return max(self.maxDepth(root.left),self.maxDepth(root.right))+1
        else:
            return -1
```

