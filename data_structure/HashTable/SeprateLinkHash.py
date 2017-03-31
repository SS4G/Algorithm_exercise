# coding=utf-8
# Author: SS4G
# Date 2017/03/30
# all test case passed

import random
class HashNode:
    """
    哈希链表的节点
    """
    def __init__(self, keyword=None, val=None):
        self.val = val
        self.keyword = keyword
        self.next = None


class Hash_SepreateLink:
    """
    使用分离链接发构建的哈希表
    调用构造函数返回的是哈希表对象
    关于测试：测试用例的插入不包括相同的关键字插入多次
    """
    def __init__(self, table_size=10, hash_function=None, lambda_val=None):
        """
        在创建哈希表时就指定好表的大小
        :param table_size:
        """
        self.table_size = table_size  # 表的总空间
        self.hashed_size = 0  # 当前表中已有的元素
        self.table = self.create_hash_table(table_size=table_size)
        # 哈希值计算函数 两个参数一个是要哈希的关键字 另一个是哈希表的大小
        self.hash_fun = hash_function if hash_function is not None else self.hash_fun0
        self.lambda_val = 0.5 if lambda_val is None else lambda_val

    def hash_fun0(self, keyword, table_size=10):
        """
        :param keyword:要插入到哈希表中的关键字
        :param table_size:
        :return: 返回求得的哈希值
        暂时使用求余数运算 是一个可以替换的函数
        """
        return keyword % table_size

    def create_hash_table(self, table_size=10):
        """
        创建了一个空的表 等待后续的链接
        :param table_size:
        :return:
        """
        list0 = [None, ]*table_size
        return list0

    def insert(self, keyword, val):
        """
        将关键字插入到哈希表中指定的位置
        对于相同的哈希映射 关键字 插入到链表头部
        :param keyword:
        :return:
        """
        hashed_key = self.hash_fun(keyword=keyword, table_size=self.table_size)
        tmp = HashNode(keyword=keyword, val=val)
        if self.table[hashed_key] is not None:
            tmp.next = self.table[hashed_key]  # 将新的节点插入到链表的头部 最新插入的数据最可能被用到
        self.table[hashed_key] = tmp
        self.hashed_size += 1
        if self.hashed_size/self.table_size > self.lambda_val:
            self.rehash()

    def query(self, keyword):
        """
        根据关键字查询到相关的单元 返回相关的单元的值的引用
        :param key_work:
        :return: handler if find else None
        """
        hashed_key = self.hash_fun(keyword=keyword, table_size=self.table_size)
        if self.table[hashed_key] is not None:
            tmp = self.table[hashed_key]
            while tmp.keyword != keyword and (tmp is not None):
                tmp = tmp.next
            return None if tmp is None else tmp
        else:
            return None

    def rehash(self):
        """
        当超过哈希表的装填因子lambda时 触发再哈希
        :return:
        """
        print("rehash invoked!", self.table_size)
        new_hash_table = Hash_SepreateLink(table_size=2*self.table_size, hash_function=self.hash_fun)
        for keyword_head in self.table:
            tmp = keyword_head
            while tmp is not None:
                new_hash_table.insert(keyword=tmp.keyword,val=tmp.val)
                tmp = tmp.next
        self.table = new_hash_table.table
        self.table_size = new_hash_table.table_size
        self.hashed_size = new_hash_table.hashed_size


class HashTable_tb:
    "testbench"
    def gen_test_case(self):
        keys = set([random.randint(0, 1999) for i in range(1, 1000)])
        testcases = [None, ]*len(keys)
        i = 0
        src_char = "ABCDEFGHIJKMLNOPQRSTUVWXYZ"
        for key in keys:
            # 一个随机的长度为10的字符串
            str0 = "".join([src_char[index] for index in [random.randint(0,25) for i in range(10)]])
            testcases[i] = (key, str0)
            i += 1
        return testcases

    def save_testcase(self,testcases):
        f = open("Hashtable_testcase.txt", "w", encoding="utf-8")
        for case in testcases:
            f.write("\t".join((str(case[0]),case[1],"\n")))
        f.close()

    def load_testcase(self):
        testcases = []
        f = open("Hashtable_testcase.txt", "r", encoding="utf-8")
        for line in f:
            sp = line.strip().split("\t")
            testcases.append((int(sp[0]), sp[1]))
        f.close()
        return testcases

    def main_testbench(self):
        operations = [2, ]
        for operation in operations:
            if operation == 1:
                testcase0 = self.gen_test_case()
                self.save_testcase(testcase0)
            elif operation == 2:
                testcase1 = self.load_testcase()
                s_hashtable = Hash_SepreateLink()
                for case in testcase1:
                    s_hashtable.insert(keyword=case[0], val=case[1])
                for case in testcase1:
                    # print(case)
                    assert  s_hashtable.query(keyword=case[0]).val == case[1], str(case[0])+":"+case[1]+":"+s_hashtable.query(keyword=case[0]).val
# Test
if __name__ == "__main__":
    t = HashTable_tb()
    t.main_testbench()








