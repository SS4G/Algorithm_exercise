import copy
class Solution(object):
    def __init__(self):
        
        self.path_stack_p=[]
        self.path_stack_q=[]
        self.general_stack=[]
    def lowestCommonAncestor(self, root, p, q):
        """
        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        if root==None:
            return None
        else:
            p_id=id(p)
            q_id=id(q)
            self.traverse(root,p_id,True )
            self.traverse(root,q_id,False)
            min_len=min(len(self.path_stack_p),len(self.path_stack_q))
            
            if min_len==1:
                return root
            else:
                last_node=self.path_stack_p[0]
                for i in range(min_len-1):
                    if id(self.path_stack_p[i+1])!=id(self.path_stack_q[i+1]):
                        break
                    last_node=self.path_stack_p[i+1]
                return last_node 
        
        
        
    def traverse(self,root,id_lookfor,is_p):
        if root!=None:
            self.general_stack.append(root)
            if id(root)==id_lookfor:
                if is_p:
                    self.path_stack_p=copy.copy(self.general_stack)
                else:
                    self.path_stack_q=copy.copy(self.general_stack)
            self.traverse(root.left,id_lookfor,is_p)
            self.traverse(root.right,id_lookfor,is_p)
            self.general_stack.pop()