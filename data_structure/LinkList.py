# coding=utf-8
# Author: SS4G
# Date 2017/03/08
# all test case passed


class Node:
    def __init__(self, val=None):
        self.next = None  # 下一指针
        self.val = val  # 值引用
        self.pre = None  # 前驱指针


class LinkList:
    "链表功能 将真正的一个链表封装称为一个对象"
    def __init__(self, val_list=None, headnode=True, double_direction=True):
        self.size = len(val_list)  # 当前链表中的节点数目不包括头结点
        self.has_head_node = headnode
        self.is_double_linklist = double_direction
        self.list_handler = self.create_linklist(val_list=val_list, headnode=headnode,
                                                 double_direction=double_direction)  # 创建的链表的存储句柄

    def create_single_list(self, val_list):
        """
        根据val_list实现一个单向链表
        :param val_list: 值列表 为空则返回None
        :return: 返回对应的列表
        """
        val_length = len(val_list)
        if val_length == 0:
            return None
        link_root = Node(val_list[0])
        tmp = link_root
        if val_length >= 1:
            for val in val_list[1:]:
                tmp.next = Node(val)
                tmp = tmp.next
        return link_root

    def create_double_list(self, val_list):
        """
        根据val_list实现一个双向链表
        :param val_list:
        :return:
        """
        val_length = len(val_list)
        if val_length == 0:
            return None
        link_root = Node(val_list[0])
        tmp = link_root
        tmp_pre = link_root
        if val_length >= 1:
            for val in val_list[1:]:
                tmp.next = Node(val)
                tmp = tmp.next
                tmp.pre = tmp_pre
                tmp_pre = tmp
        return link_root

    def create_linklist(self, val_list=None, headnode=True, double_direction=True):
        """
        :param val_list: 值列表
        :param headnode: 是否需要头结点 头结点用特殊值 "&HEAD"+str(id(head)) 标示以保证特殊性
        :param double_direction: 是否构建双向链表
        :return:
        """
        if headnode:
            head = Node()
            res = head
            head.val = "&HEAD"+str(id(head))
            if double_direction:
                res.next = self.create_double_list(val_list)
                res.next.pre = res
            else:
                res.next = self.create_single_list(val_list)
            return res
        else:
            if double_direction:
                res = self.create_double_list(val_list)
            else:
                res = self.create_single_list(val_list)
            return res

    def find_node_by_index(self, index):
        """
        查找对应索引值的节点
        索引值不包括头结点 收个节点的索引值为0
        :param link_list: 目标链表
        :param index: 要搜索的索引 [0,size-1]
        :return: 对应节点的引用 若未找到对应下标的节点 返回None
        """
        if self.size <= index:
            print("the index is too large!")
            return None
        else:
            tmp = self.list_handler.next if self.has_head_node else self.list_handler
            i = 0
            while i < index:
                i += 1
                tmp = tmp.next
            return tmp

    def find_node_by_value(self, value):
        """
        找到对应值的第一个节点 返回其引用
        :param link_list: 目标链表
        :param value:
        :return:
        """
        tmp = self.list_handler.next if self.has_head_node else self.list_handler
        while tmp is not None:
            if tmp.val == value:
                return tmp
            else:
                tmp = tmp.next
        print("the value not found")
        return None

    def is_empty(self):
        """
        :return: 返回链表是否为空
        """
        return False if self.size > 0 else True

    def size(self):
        """
        :return: 返回链表中的当前节点数目不包含头结点
        """
        return self.size

    def insert_node(self, prenode, node_val):
        """
        :param prenode: 前驱节点引用 prenode 可以为头结点但是不能是None
                        在非头结点处插入不能使用该方法
        :param node_val: 要插入的值
        :return:
        """
        if not self.is_double_linklist:
            # A->C
            # A->B->C B is inserted
            nodeA = prenode
            nodeC = prenode.next
            nodeB = Node(node_val)

            nodeA.next = nodeB
            nodeB.next = nodeC
        else:
            # A->C
            # A->B->C B is inserted
            nodeA = prenode
            nodeC = prenode.next
            nodeB = Node(node_val)

            nodeA.next = nodeB
            nodeB.pre = nodeA
            nodeB.next = nodeC
            if nodeC is not None:
                nodeC.pre = nodeB

    def insert(self, index, node_value):
        """
        :param node_value 要插入的node对象
        :param index:
        :return:
        """
        assert index >= 0, "ERROR:index prameter is invalid!"
        assert index <= self.size, "ERROR index is out of range while insert "
        
        if index == 0:
            if self.has_head_node:  # 若有头结点
                self.insert_node(self.list_handler, node_val=node_value)
            else:   # 没有头结点的情况下可能没有前驱，需要把新建的节点直接插到头部
                new_node = Node(node_value)
                new_node.next = self.list_handler
                self.list_handler.pre = new_node
                self.list_handler = new_node

        else:
            pre_node = self.find_node_by_index(index-1)
            self.insert_node(pre_node, node_val=node_value)
        self.size += 1

    def delete(self, index):
        """
        :param index: 要删除的节点的索引 该索引对应的节点会被删除
        :return: None
        """
        assert index < self.size, "ERROR: index is out of range! while delete"
        if index == 0:
            if self.has_head_node:
                current_node = self.list_handler.next
                self.list_handler.next = current_node.next
                if current_node.next is not None:  # only valid when self.is_double_linklist is true
                    current_node.next.pre = self.has_head_node
            else:
                current_node = self.list_handler
                self.list_handler = current_node.next
                if current_node.next is not None:  # only valid when self.is_double_linklist is true
                    current_node.next.pre = self.has_head_node
        else:
            pre_node = self.find_node_by_index(index - 1)
            current_node = pre_node.next
            pre_node.next = current_node.next
            if current_node.next is not None:  # maybe current node is the last node of link list
                current_node.next.pre = pre_node   # only valid when self.is_double_linklist is true
        self.size -= 1

    def go_previous(self, node):
        assert self.is_double_linklist, "the lisk list is single linklist"
        return node.pre

    def go_next(self, node):
        return node.next

    def show(self):
        """
        将一个链表重头到尾打印出来
        需要实现node类的__str__()方法 才可以被打印
        :param link_list:
        :return:
        """
        tmp = self.list_handler
        disp_list = []
        while True:
            disp_list.append(str(tmp.val))
            if tmp.next is not None:
                disp_list.append("->")
                tmp = tmp.next
            else:
                disp_list.append("->END")
                break
        print("".join(disp_list))

# Test
if __name__ == "__main__":
    testcase = [
        # init_list - head  - double - insert_index - find_index - find_value
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], False, False, 3, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], False, True, 3, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, False, 3, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 3, 6, "9"),

        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 0, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 0, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 0, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 0, 6, "9"),

        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 10, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 10, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 10, 6, "9"),
        (["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"], True, True, 10, 6, "9"),

    ]
    i = 0
    for case0 in testcase:
        print("  ")
        print("  ")
        print("running testcase ", i)
        init_list = case0[0]
        has_head = case0[1]
        isdouble = case0[2]
        insert_index = case0[3]
        find_index = case0[4]
        find_value = case0[5]

        l0 = LinkList(init_list, has_head, isdouble)
        l0.show()
        # l0 = LinkList([1, 2, 3], True, False)
        res = l0.find_node_by_value(find_value)
        assert res.val == find_value, "find value failed!"

        res = l0.find_node_by_index(find_index)
        assert res.val == case0[0][find_index], "l0 find index failed!"

        print("value instered")
        l0.insert(insert_index, "INSERT_VALUE")
        l0.show()

        print("value deleted")
        l0.delete(insert_index)
        l0.show()

        i += 1


