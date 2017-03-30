# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
from operator import itemgetter
class Solution(object):
    def __init__(self):
        self.level=0
        self.node_list=[]
    def tra(self,root=None,root_level=0,level_offset=0):
        if root!=None:
            self.node_list.append((self.GetLeStIndex(root_level)+level_offset,root_level,root.val))
            print("root_val=", root.val, "root_index=", self.GetLeStIndex(root_level)+level_offset, "root_level=", root_level, "root_offset=",
                  level_offset)
            self.tra(root=root.left ,root_level=root_level+1,level_offset=level_offset*2)#left sub tree
            self.tra(root=root.right,root_level=root_level+1,level_offset=level_offset*2+1)#left sub tree

    def GetLeStIndex(self,level):
        """
        get start index in the total tree for level
        :param level:
        :return:
        """
        return 2**(level-1)-1

    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        if root==None:
            return []
        else:
            self.tra(root,1,0)
            node_list2=sorted(self.node_list,key=itemgetter(0),reverse=False)
            cur_level=0
            res= []
            for node in node_list2:

                if cur_level!=node[1]:
                    res.append([])
                    cur_level=node[1]
                res[cur_level-1].append(node[2])
            return res

s=Solution()
a=TreeNode('a')
b=TreeNode('b')
c=TreeNode('c')
d=TreeNode('d')
e=TreeNode('e')
f=TreeNode('f')
a.left=b
a.right=c
b.left=d
b.right=e
c.right=f
s.levelOrder(a)