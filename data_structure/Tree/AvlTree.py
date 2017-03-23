
from Algorithm.data_structure.Tree.BinarySearchTree import BinarySearchTree, bstNode


class AvlNode(bstNode):
    def __init__(self, height=-1):
        bstNode.__init__(self)
        self.height = -1


class AvlTree(BinarySearchTree):

    def __init__(self, init_list = None):
        BinarySearchTree.__init__()

    def createAvlTree(self):
        pass

    def Avl_insert(self, root, val):
        """
        这种插入会跟踪的修改路径上节点的高度
        若树中已经有了相同值的节点 则直接放弃 不做任何处理
        一个节点的高度为 max(left_height,right_height)+1
        :param root:
        :param val:
        :return:
        """
        path_stack = []
        ptr = root
        while ptr is not None:  # insert the node first
            path_stack.append(ptr)
            if val < ptr.val:
                if ptr.left is None:  # insert here
                    ptr.left = AvlNode(0)
                    path_stack.append(ptr.left)
                    break
                else:  # you still need find
                    ptr = ptr.left
            elif val > ptr.val:
                if ptr.right is None:  # insert here
                    ptr.right = AvlNode(0)
                    path_stack.append(ptr.right)
                    break
                else:  # you still need find
                    ptr = ptr.right
            else:  # the value is already exist
                return 

        # back track to find the out of balanced tree node

    def single_rotate_tree(self, root, is_left=True):

    def double_rotate_tree(self, root, is_left=True):




    def mark_depth(self, root):
        if root is None:
            return -1
        else:
            root.l_depth = self.mark_depth(root.left)
            root.r_depth = self.mark_depth(root.right)
            my_depth = max(root.l_depth, root.r_depth) + 1
            root.depth = my_depth

            if root.l_depth == -1 and root.r_depth == -1:  # leaf node
                self.path_sum.append((root, 0))
            elif root.l_depth == -1:
                self.path_sum.append((root, root.r_depth + 1))
            elif root.r_depth == -1:
                self.path_sum.append((root, root.l_depth + 1))
            else:
                self.path_sum.append((root, root.l_depth + root.r_depth + 2))
            return my_depth


