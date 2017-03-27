# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

#final_state:WA
class Solution(object):
    def __init__(self):
        self.suf_fix_res=[]
        self.mid_fix_res=[]
            
 
    def isSameTree(self, p, q):
        """
        :type p: TreeNode
        :type q: TreeNode
        :rtype: bool
        """
        self.suf_fix_res=[]
        self.mid_fix_res=[]
        self.mid_fix(p)
        self.suf_fix(p)
        suf0=self.suf_fix_res
        mid0=self.mid_fix_res
        self.suf_fix_res=[]
        self.mid_fix_res=[]
        self.mid_fix(q)
        self.suf_fix(q)
        suf1=self.suf_fix_res
        mid1=self.mid_fix_res
        if suf1==suf0 and mid1==mid0:
            return True
        else:
            return False
        
        
        
    def mid_fix(self,p):
        if p!=None:            
            self.mid_fix(p.left)
            self.mid_fix_res.append(p.val)
            self.mid_fix(p.right)
            
    def suf_fix(self,p):
        if p!=None:            
            self.suf_fix(p.left)
            self.suf_fix(p.right)
            self.suf_fix_res.append(p.val)
            
            