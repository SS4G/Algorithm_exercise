# leetcdoe 100
## Question
#### Same Tree
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

##### Caution
注意 判断两个二叉树是否在结构上与节点值上完全一致，不能仅仅通过检查遍历后的序列来断定 此处举个反例，如果两个树的节点个数相同且所有节点的值都为一个值的话那么遍历出来的结果不管是什么顺序的都是一样的。所谓的中序遍历加上其他任意一个前后序遍历的结果能唯一确定亦可二叉树，是在每个节点都认为其值是不同的情况下的才能成立

所以这个题目不可以用这个方法来完成

## Answer
使用递归的方法 直接看代码吧 比较易懂
##### code
```
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

#final_state:AC
class Solution(object):
    def isSameTree(self, p, q):
        """
        :type p: TreeNode
        :type q: TreeNode
        :rtype: bool
        """
        if p!=None and q!=None:
            if p.val!=q.val:
                return False 
                
            if self.isSameTree(p.left,q.left) and self.isSameTree(p.right,q.right) :
                return True
            else :
                return False
        elif p==None and q==None:
            return True
        else:
            return False
```
