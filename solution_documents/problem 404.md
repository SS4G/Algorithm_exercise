# leetcdoe 404
## Question 
#### Sum of Left Leaves
Find the sum of all left leaves in a given binary tree.

Example:

```Python
....3
   / \
  9  20
    /  \
   15   7
```


There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

## Answer
当然是使用递归的思想，去问问自己的左右子树的和是多少然后加起来 然后告诉上一层
重点是 对于各个节点请款的考虑，如何设定一个互斥的一个全局的划分 即使得任意一种节点可以被唯一的划分到一个分类中去
- 本题目的划分为以下几类


```
*  root==None
*  没有节点 
*  root.left==None and root.right==None
*  本身是一个叶子节点 
* (root.left!=None) and (root.left.left==None and root.left.right==None)
*  有一个左叶子节点
* (root.left==None) 
  没有左叶子或者左子树
* (root.left!=None) and (root.left.left==None and root.left.right==None)
  有左子树
  
  因为只关心左叶子节点 所以对于右子树是什么情况并不关心 直接丢给递归处理
  注意这些条件是有顺序的
  在代码中的表示是通过elif实现的 即下一个条件的附加前提是之前的所有条件全部不成立
```



##### code (beat 70% Python)


```
class Solution(object):
    def sumOfLeftLeaves(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root==None:
            return 0
        elif root.left==None and root.right==None:# if i'm a leaf
            return 0
        elif (root.left!=None) and (root.left.left==None and root.left.right==None):#if i have a left leaf
            return root.left.val+self.sumOfLeftLeaves(root.right)
        elif (root.left==None) :
            return self.sumOfLeftLeaves(root.right)
        elif root.left!=None:
            return self.sumOfLeftLeaves(root.left)+self.sumOfLeftLeaves(root.right)
```
