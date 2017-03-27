# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

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
 #   3
 #  / \
 # 9  20
 #   /  \
 #  15   7
a=TreeNode(3 )
b=TreeNode(9 )
c=TreeNode(20)
d=TreeNode(15)
e=TreeNode(7)
#f=TreeNode("f")
#g=TreeNode("g")
#h=TreeNode("h")
#i=TreeNode("i")

a.left =b 
a.right=c 

b.left =None 
b.right=None 


c.left =d 
c.right=e

d.left =None
d.right=None 

e.left =None 
e.right=None 

# f.left =None 
# f.right=None 
# 
# g.left =None 
# g.right=i 
#         
        
s=Solution()
print(s.sumOfLeftLeaves(a))
        
        
        