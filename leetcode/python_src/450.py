class Solution:

    def deleteNode(self, root, key):
        """
        :type root: TreeNode
        :type key: int
        :rtype: TreeNode
        """
        return self.delete_in_bst(root, key)

    def find_in_bst(self, bst_root, val):
        tmp = bst_root
        while tmp is not None:
            if tmp.val == val:
                return tmp
            elif tmp.val > val:
                tmp = tmp.left
            elif tmp.val < val:
                tmp = tmp.right
        return None  # the value you required is not found


    def find_max_val(self, bst_root):
        """
        找出最（大下）小值 返回该值
        :param bst_root:
        :return:
        """
        tmp = bst_root
        while tmp is not None:
            if tmp.right is not None:
                tmp = tmp.right
            else:
                return tmp.val


    def find_max_node(self, bst_root):
        """
        找出最（右下）大值节点 返回该节点的引用
        :param bst_root:
        :return:
        """
        tmp = bst_root
        while tmp is not None:
            if tmp.right is not None:
                tmp = tmp.right
            else:
                return tmp


    def find_min_val(self, bst_root):
        """
        找出最（左下）小值 返回该值
        :param bst_root:
        :return:
        """
        tmp = bst_root
        while tmp is not None:
            if tmp.left is not None:
                tmp = tmp.left
            else:
                return tmp.val


    def find_min_node(self, bst_root):
        """
        找出最（左下）小值节点 返回该节点的引用
        :param bst_root:
        :return:
        """
        tmp = bst_root
        while tmp is not None:
            if tmp.left is not None:
                tmp = tmp.left
            else:
                return tmp


    def insert_2_bst(self, bst_root, val):
        """
        将指定的值插入到bst的指定位置 如果已有 将数量域加1
        :param root:
        :param val:
        :return:
        """
        tmp = bst_root
        while tmp is not None:
            if val > tmp.val:
                if tmp.right is None:
                    tmp.right = TreeNode(val)
                    break
                else:
                    tmp = tmp.right
            elif val == tmp.val:
                break
            elif val < tmp.val:
                if tmp.left is None:
                    tmp.left = TreeNode(val)
                    break
                else:
                    tmp = tmp.left


    def delete_in_bst(self, bst_root, val, lazy=False):
        """
        删除对应值的节点 lazy 表示是否为懒惰删除
        其实这个方法应该改进为更加递归的方法
        :param bst_root:
        :param val:
        :param lazy:
        :return: 返回删除后的bst的根节点引用
        """
        if lazy:  # lazy delete just mark delete flag for the note
            node = self.find_in_bst(bst_root, val)
            if node is not None:  # the value exist in the bst
                node.del_flag = True
            return bst_root
        else:
            # print("to del value ", val)
            last = bst_root  # 用于跟踪待删除节点的父节点
            tmp = bst_root
            which_son = "self"
            # 找到要删除的值所在的节点 并记录他的父节点 以及他是父节点的哪个孩子
            while tmp is not None:
                if tmp.val > val:
                    last = tmp  # 保存为父节点
                    which_son = "left"
                    tmp = tmp.left
                elif tmp.val < val:
                    last = tmp  # 保存为父节点
                    which_son = "right"
                    tmp = tmp.right
                elif tmp.val == val:  # 查找到对应的节点酒退出
                    break

            if tmp is None:
                return bst_root  # 没有找到要删除的节点
            elif tmp is bst_root:  # 要删除的是bst的根节点
                me = bst_root

                if (me.left is None) and (me.right is None):  # 左右都为空 连根节点都删除了 就返回一个空树
                    return None
                elif (me.left is None) or (me.right is None):  # 左边或者右边为空 直接返回左子树或者右子树
                    return me.left if me.left is not None else me.right
                else:  # 两边全部不为空
                    # 获取右子树的最小值的节点
                    right_min_node = self.find_min_node(bst_root.right)
                    # 将右子树最小值的节点的信息复制到要删除的节点中 实际上并不删除这个节点
                    me.val = right_min_node.val
                    me.val_amount = right_min_node.val_amount
                    # 要删除的节点的右子树应当更新为删除了最小值节点的右子树
                    me.right = self.delete_in_bst(me.right, me.val)  # 递归的删除右子树中的替换值
                    return bst_root
            else:
                father = last  # 要删除的节点的父节点
                me = tmp  # 要删除的节点

                if (me.left is None) and (me.right is None):  # 要删除的是叶子节点
                    if which_son == "left":
                        father.left = None  # 直接置空付清对应的指针域 让父亲给抛弃自己
                    else:
                        father.right = None

                elif (me.left is None) or (me.right is None):  # 要删除的节点只有一侧子树
                    if which_son == "left":
                        father.left = me.left if me.left is not None else me.right  # 直接将子树拼接到父亲的对应位置
                    else:
                        father.right = me.left if me.left is not None else me.right

                else:  # 两边全部不为空
                    right_min_node = self.find_min_node(me.right)
                    me.val = right_min_node.val  # 同上一部分 并不实际删除 只是修改信息
                    me.right = self.delete_in_bst(me.right, me.val)  # 递归的删除右子树中的替换值
                return bst_root  # 返回根部
