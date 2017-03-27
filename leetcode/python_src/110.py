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

    