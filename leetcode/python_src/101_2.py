class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def __init__(self):
        self.mark_list=[]
        self.max_line=0

    def line_offset(self,line):
        """ 
        root node index is 0 
        line index start from 1
        empty tree has 0 line
        """
        if line==1:
            return 0
        else:
            return 2**(line-1)-1
    
    def mark_tree_node(self,root,line):        
        """
        :type root: TreeNode
        :rtype: List[TreeNode,]
        """
        if root!=None:
            self.mark_list.append(root)
            if root.left!=None:
                root.left.abs_index =self.line_offset(line+1)+(root.abs_index-self.line_offset(line))*2
                if self.max_line<line+1:
                    self.max_line=line+1
                self.mark_tree_node(root.left,line+1)
            if root.right!=None:
                root.right.abs_index=self.line_offset(line+1)+(root.abs_index-self.line_offset(line))*2+1
                if self.max_line<line+1:
                    self.max_line=line+1
                self.mark_tree_node(root.right,line+1)
        return None
    
    def convert_tree_to_list(self,root):
        """
        :type root: TreeNode
        :rtype: List[TreeNode,]
        """
        if root!=None:
            root.abs_index=0
            self.mark_tree_node(root,1)
            
            tree_node_array=[None,]*self.line_offset(self.max_line+1)
            for i in self.mark_list:
                tree_node_array[i.abs_index]=i

                
            return tree_node_array
        else :
            return [None,]


    
    def isSymmetric(self,root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        tree_node_array=self.convert_tree_to_list(root)    
        if tree_node_array[0]==None:
            return True
        elif len(tree_node_array)==1:
            return True
        else:
            for line_index in range(2,self.max_line+1):

                if not self.check_symmetry(tree_node_array[self.line_offset(line_index):self.line_offset(line_index+1)]):
                    return False
            return True
                    
                    
    def check_symmetry(self,node_list):    
        length=len(node_list)
        length_2=length>>1
       
        if length&0x01!=0:
            return False
        else:
            for i in range(length_2):
                if node_list[i]==None and node_list[length-1-i]==None:
                    continue 
                elif node_list[i]!=None and node_list[length-1-i]!=None:
                    if node_list[i].val!=node_list[length-1-i].val:
                        return False   
                else :
                    return False
            return True    
                    
a=TreeNode("1")
b=TreeNode("2")
c=TreeNode("2")
d=TreeNode("3")
e=TreeNode(None)
f=TreeNode("5")
g=TreeNode("3")
#h=TreeNode("h")
#i=TreeNode("i")

a.left =b 
a.right=c 

b.left =d 
b.right=e 


c.left =f 
c.right=g 

d.left =None
d.right=None 

e.left =None 
e.right=None 

f.left =None 
f.right=None 

g.left =None 
g.right=None          


s=Solution()     
print(s.isSymmetric(a)) 

        
        
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        