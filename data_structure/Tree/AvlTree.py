from Algorithm.data_structure.Tree.BinarySearchTree import BinarySearchTree, bstNode


class AvlNode(bstNode):
    def __init__(self, depth=-1):
        bstNode.__init__(self)
        self.depth = -1


class AvlTree(BinarySearchTree):

    def __init__(self, init_list = None):
        BinarySearchTree.__init__()

    def createAvlTree(self):
        pass

    def mark_depth(self, root):
        if root is None:
           return -1
        if root.left is None and root.right is None:
            root.depth = 0
            return 0
        elif root.left is None:

        elif root.right is None:

        elif

