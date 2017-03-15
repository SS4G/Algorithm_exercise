# coding=utf-8
# Author: SS4G
# Date 2017/03/09
# Status:finished


class Node:
    """
    二叉树的节点
    """
    def __init__(self, val=None):
        """
        :param val:
        """
        self.val = val  # 节点值
        self.left = None  # 左孩子的引用
        self.right = None  # 有孩子的引用
        self.index = None  # 在满二叉树中的绝对位置 根节点为0
    def __str__(self):
        return self.val


class BinaryTree:
    """
    二叉树的相关方法
    该二叉树是链式结构

    统一标准:
    二叉树的根节点编号为0
    根节点所在的层为0
    """
    def __init__(self, init_val_list=None):
        """
        根据列表中的值创建一个二叉树
        :param init_val_list:
        """
        #   0
        #  / \
        # 1   2 ...
        # 若以0 为开始的根节点
        # 一个节点在满二叉树中的坐标为n 坐标标记方法为从左到右 从上到下
        # 其左孩子的坐标为 2*n+1 右孩子的坐标为 2*n+2
        length = len(init_val_list)
        if init_val_list is not None:
            root = Node(val = init_val_list[0])

    def add_node(self, this_node=None, this_node_index=0,
                    init_val_length=0, init_arr=None):
        """

        :param this_node: 当前节点的 引用
        :param this_node_index: 当前节点在满二叉树中的位置
        :param init_val_length: 初始化时yo
        :param init_arr: 初始值使用的列表
        :return:
        """
        left_son_index = this_node_index*2+1
        right_son_index = this_node_index*2+2
        if left_son_index < init_val_length:
            this_node.left = Node(init_arr[left_son_index])
            self.add_node(this_node.left, )

    def construct_tree(self, val_list, max_node_index):




    def print_tree(self, tree, level=0, level_mark="····"):
        """
        将二叉树按照层次结构打印出来
        使用类似于文件目录结构的方式
        tree 为根节点时 level参数使用默认的0即可
        :param tree: 当前的节点
        :param level:当前打印的节点所处的层数
        :param level_mark: 打印每层前面的字符
        :return:
        """
        if tree is None:  # the node is leaf
            return None
        else:
            print(" " + level * level_mark + str(tree))
            self.print_tree(tree.left, level=level+1)
            self.print_tree(tree.right, level=level + 1)

    def recursion_pre_traverse(self):
        """
        递归前序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def recursion_mid_traverse(self):
        """
        递归中序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def recursion_post_traverse(self):
        """
        递归后序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def non_recursion_pre_traverse(self):
        """
        非递归前序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def non_recursion_mid_traverse(self):
        """
        非递归中序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def non_recursion_post_traverse(self):
        """
        非递归后序遍历
        :return: 返回一个遍历结果的值的列表
        """

    def level_order_traverse(self):
        """
        二叉树层次遍历
        :return: 返回一个遍历结果的值的列表的列表
        每个子列表 代表一个层次 根节点所在的层为0
        """


# Test
if __name__ == "__main__":
    a = Node("A")
    b = Node("B")
    c = Node("C")
    d = Node("D")
    e = Node("E")
    f = Node("F")
    g = Node("G")
    h = Node("H")

    a.left = b
    a.right = c
    b.left = d
    b.right = e
    c.left = f
    c.right = g
    d.left = h

    binarytree = BinaryTree()
    binarytree.print_tree(a)

