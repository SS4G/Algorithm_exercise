class Solution(object):
    def invertTree(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        if root!=None:
            if root.left!=None or root.right!=None:
                root.left,root.right=root.right,root.left
                self.invertTree(root.left)
                self.invertTree(root.right)
        return root
        

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
            


    def isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        if root==None:
            return True
        elif root.left==None and root.right==None:
            return True
        elif root.left!=None and root.right!=None:
            reversed_left=self.invertTree(root.left)
            return isSameTree(reversed_left,root.right)
        else:
            return False
        
                
     