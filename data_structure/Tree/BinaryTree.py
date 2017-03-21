# coding=utf-8
# Author: SS4G
# Date 2017/03/09
# Status:finished


class Node:
    """
    二叉树的节点
    """
    def __init__(self, val=None, node_index=-1):
        """
        :param val:
        """
        self.val = val  # 节点值
        self.left = None  # 左孩子的引用
        self.right = None  # 有孩子的引用
        self.index = node_index  # 在满二叉树中的绝对位置 根节点为0

    def __str__(self):
        # return str(self.val)+","+str(self.index)
        return str(self.val)


class BinaryTree:
    """
    二叉树的相关方法
    该二叉树是链式结构

    统一标准:
    二叉树的根节点编号为0
    根节点所在的层为0
    """
    def __init__(self):
        pass

    def create_binary_tree(self, init_val_list=None):
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
            root = Node(val=init_val_list[0], node_index=0)
            self.add_node(root, 0, length, init_val_list)
        else:
            root = None
        return root

    def add_node(self, this_node=None, this_node_index=0, init_val_length=0, init_arr=None):
        """
        使用类似于先序遍历 但是这个遍历是假设的
        如果遍历到的节点在初始化列表中可以找的到就添加这个节点 否则就当没有
        :param this_node: 当前节点的 引用
        :param this_node_index: 当前节点在满二叉树中的位置
        :param init_val_length: 初始化时yo
        :param init_arr: 初始值使用的列表
        :return:
        """
        left_son_index = this_node_index*2+1
        right_son_index = this_node_index*2+2
        if left_son_index < init_val_length:
            val = init_arr[left_son_index]
            if val is not None:
                this_node.left = Node(val, 2*this_node_index+1)
                self.add_node(this_node.left, this_node_index=2*this_node_index+1,
                              init_val_length=init_val_length, init_arr=init_arr)
        if right_son_index < init_val_length:
            val = init_arr[right_son_index]
            if val is not None:
                this_node.right = Node(val, 2*this_node_index+2)
                self.add_node(this_node.right, this_node_index=2*this_node_index+2,
                              init_val_length=init_val_length, init_arr=init_arr)

    # def print_tree(self, tree, level=0, level_mark="····"):
    def print_tree(self, tree, level=0, level_mark="----"):
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
            if (tree.left is None) and (tree.right is not None):  # 确保在左右子树只有一个的情况下不会分不清左右
                print(" " + (level+1) * level_mark+"-")
            self.print_tree(tree.left, level=level+1)
            if (tree.right is None) and (tree.left is not None):
                print(" " + (level+1) * level_mark+"-")
            self.print_tree(tree.right, level=level + 1)

    def recursion_pre_traverse(self, root):
        """
        递归前序遍历
        :return: 返回一个遍历结果的值的列表
        """
        traverse_res = []
        if root is not None:
            traverse_res.append(root.val)
            traverse_res.extend(self.recursion_pre_traverse(root.left))
            traverse_res.extend(self.recursion_pre_traverse(root.right))
        return traverse_res

    def recursion_mid_traverse(self, root):
        """
        递归中序遍历
        :return: 返回一个遍历结果的值的列表
        """
        traverse_res = []
        if root is not None:
            traverse_res.extend(self.recursion_mid_traverse(root.left))
            traverse_res.append(root.val)
            traverse_res.extend(self.recursion_mid_traverse(root.right))
        return traverse_res

    def recursion_post_traverse(self, root):
        """
        递归后序遍历
        :return: 返回一个遍历结果的值的列表
        """
        traverse_res = []
        if root is not None:
            traverse_res.extend(self.recursion_post_traverse(root.left))
            traverse_res.extend(self.recursion_post_traverse(root.right))
            traverse_res.append(root.val)
        return traverse_res

    def non_recursion_pre_traverse(self, root):
        """
        非递归前序遍历
        :return: 返回一个遍历结果的值的列表
        """
        # enum = ["just_pushed", "printed", "left_traversed", "right_traversed"]
        travese_res = []
        stack = []
        if root is not None:
            # 堆栈中的节点有三种
            stack.append([root, "just_pushed"])
            while len(stack) > 0:  # 使用一个状态域来记录节点的状态
                if stack[-1][1] == "just_pushed":  # 子节点均为遍历的情况下 输出值
                    stack[-1][1] = "printed"  # 状态更新
                    travese_res.append(stack[-1][0].val)

                elif stack[-1][1] == "printed":  # 栈顶节点左子树未遍历
                    stack[-1][1] = "left_traversed"  # 修改老节点的左标记
                    if stack[-1][0].left is not None:
                        stack.append([stack[-1][0].left, "just_pushed"])

                elif stack[-1][1] == "left_traversed":  # 栈顶节点右子树未遍历
                    stack[-1][1] = "right_traversed"  # 修改老节点的左标记
                    if stack[-1][0].right is not None:
                        stack.append([stack[-1][0].right, "just_pushed"])

                elif stack[-1][1] == "right_traversed":
                    stack.pop()
        return travese_res

    def non_recursion_mid_traverse(self, root):
        """
        非递归中序遍历
        :return: 返回一个遍历结果的值的列表
        """
        travese_res = []
        stack = []
        if root is not None:
            # 堆栈中的节点有三种
            stack.append([root, "just_pushed"])
            while len(stack) > 0:
                if stack[-1][1] == "left_traversed":  # 子节点均为遍历的情况下 输出值
                    stack[-1][1] = "printed"  # state update
                    travese_res.append(stack[-1][0].val)

                elif stack[-1][1] == "just_pushed":  # 栈顶节点左子树未遍历
                    stack[-1][1] = "left_traversed"  # 修改老节点的左标记
                    if stack[-1][0].left is not None:
                        stack.append([stack[-1][0].left, "just_pushed"])

                elif stack[-1][1] == "printed":  # 栈顶节点右子树未遍历
                    stack[-1][1] = "right_traversed"  # 修改老节点的左标记
                    if stack[-1][0].right is not None:
                        stack.append([stack[-1][0].right, "just_pushed"])

                elif stack[-1][1] == "right_traversed":
                    stack.pop()
        return travese_res

    def non_recursion_post_traverse(self, root):
        """
        非递归后序遍历
        :return: 返回一个遍历结果的值的列表
        """
        travese_res = []
        stack = []
        if root is not None:
            # 堆栈中的节点有三种
            stack.append([root, "just_pushed"])
            while len(stack) > 0:
                if stack[-1][1] == "right_traversed":  # 子节点均为遍历的情况下 输出值
                    stack[-1][1] = "printed"  # state update
                    travese_res.append(stack[-1][0].val)

                elif stack[-1][1] == "just_pushed":  # 栈顶节点左子树未遍历
                    stack[-1][1] = "left_traversed"  # 修改老节点的左标记
                    if stack[-1][0].left is not None:
                        stack.append([stack[-1][0].left, "just_pushed"])

                elif stack[-1][1] == "left_traversed":  # 栈顶节点右子树未遍历
                    stack[-1][1] = "right_traversed"  # 修改老节点的左标记
                    if stack[-1][0].right is not None:
                        stack.append([stack[-1][0].right, "just_pushed"])

                elif stack[-1][1] == "printed":
                    stack.pop()
        return travese_res

    def level_order_traverse0(self, root, level=0):
        """
        基于一条原则,对二叉树进行先序遍历 所以对于同一层级的
        节点左边的节点肯定比右边的节点先被遍历到 所以每次一旦遍历到
        一个节点就将他填充到对应层级的队列中 最后按照队列的层级
        将队列从低层级到高层级进行合并

        空的二叉树的层次为0
        只有一个节点的二叉树的的level是1
        二叉树层次遍历
        使用多个列表的方式实现
        :return: 返回一个遍历结果的值的列表的列表
        每个子列表 代表一个层次 根节点所在的层为0
        """
        if level == 0:
            self.lv_dict = {}
            if root is not None:
                self.level_order_traverse0(root, level=1)
                levels = list(self.lv_dict.keys())  # the method keys return a Iterator rather than
                levels.sort()
                res = []
                for key in levels:
                    res.extend(self.lv_dict[key])
                return res
        else:
            if root is not None:
                if level not in self.lv_dict:
                    self.lv_dict[level] = []
                self.lv_dict[level].append(root.val)
                self.level_order_traverse0(root.left, level=level+1)
                self.level_order_traverse0(root.right, level=level+1)

    def level_order_traverse1(self, root):
        """
        使用一个队列
        将根节点先放入队列
        在访问节点时就将其值放到输出中然后将他的左右孩子放入到队列中
        只要队列中还有没有被访问的节点 就一直进行循环
        所有节点被访问后 停止循环
        :param root:
        :return:
        """
        self.res1 = []
        self.first = False
        self.fifo = [root, ]
        self.cur = 0  # initliazed as null
        self.end = 1
        while self.cur < self.end:
            self.res1.append(self.fifo[self.cur].val)
            if self.fifo[self.cur].left is not None:
                self.fifo.append(self.fifo[self.cur].left)
                self.end += 1
            if self.fifo[self.cur].right is not None:
                self.fifo.append(self.fifo[self.cur].right)
                self.end += 1
            self.cur += 1
        return self.res1

    def trans_form(self, node_index_list):
        """
        将(坐标，初始值)的列表转化为完全的初始值列表
        (index,value)

        :param node_index_list:
        :return:
        """
        index_s = map(lambda tu: tu[0], node_index_list)
        max_index = max(index_s)
        tree_init_list = [None, ]*(max_index+1)
        for i in node_index_list:
            tree_init_list[i[0]] = i[1]
        return tree_init_list


# Test
if __name__ == "__main__":

    binarytree = BinaryTree()
    trees = []

    tree_full = binarytree.create_binary_tree(['0', '1', '2', '3', '4', '5', '6'])
    binarytree.print_tree(tree_full)

    # load trees from init data file
    f = open("tree_init.txt", "r", encoding="utf-8")
    lines = f.readlines()
    f.close()
    half_case = map(lambda s: s.strip().split(), filter(lambda line: len(line) > 1, lines))
    for tree in half_case:
        tree_init = []
        for node in tree:
            l = []
            l = node.split(",")
            tree_init.append((int(l[0]), l[1]))
        full_init_list = binarytree.trans_form(tree_init)
        trees.append(binarytree.create_binary_tree(init_val_list=full_init_list))

    # test
    for tree in trees:
        # binarytree.print_tree(tree)
        # 校验先序遍历结果
        binarytree = BinaryTree()  # 注意清除每次调用之后的工具内部状态
        assert binarytree.recursion_pre_traverse(tree) == binarytree.non_recursion_pre_traverse(tree), \
            "pre-traverse-error"
        assert binarytree.recursion_mid_traverse(tree) == binarytree.non_recursion_mid_traverse(tree), \
            "mid-traverse-error"
        assert binarytree.recursion_post_traverse(tree) == binarytree.non_recursion_post_traverse(tree), \
            "post-traverse-error"

        # print(",".join(binarytree.level_order_traverse0(tree)))
        # binarytree.level_order_traverse1(tree)
        print(",".join(binarytree.level_order_traverse1(tree)))
    print(",".join(binarytree.level_order_traverse0(tree_full)))
