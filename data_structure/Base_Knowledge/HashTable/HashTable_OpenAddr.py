from AlgorithmTraining.data_structure.Base_Knowledge.HashTable.SeprateLinkHash import HashNode
import random
class NextAddr:
    """
    产生下一个偏移地址的迭代器
    """
    def __init__(self):
        self.status = 0
        self.query_status = 0
        self.next_fun = lambda this_val: this_val**2

    def get_next_offset(self):
        self.status += 1  # self.next_fun(self.status)
        return self.next_fun(self.status)

    def get_next_query_offset(self):
        self.query_status += 1  # self.next_fun(self.query_status)
        return self.next_fun(self.query_status)

class HashTable_OpenAddr:
    """
    开放定址法的哈希表
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
        self.lambda_val = 0.4 if lambda_val is None else lambda_val

    def hash_fun0(self, keyword, table_size=10):
        """
        :param keyword:要插入到哈希表中的关键字
        :param table_size:
        :return: 返回求得的哈希值
        暂时使用求余数运算 是一个可以替换的函数
        """
        return keyword % table_size

    def find_next_empty_addr(self, this_addr):
        """
        计算下一个空的存放地址 用于插入操作
        :param this_addr:
        :param max_addr:
        :param next_addr: 计算下一个可用理论地址的函数 可以为线性或者二次
        :return: 计算出来的下一个可用地址
        """
        new_addr = this_addr
        while self.table[this_addr] is not None:  # 这个地址被占用了
            next_calc = NextAddr()
            new_addr = this_addr+next_calc.get_next_offset()
            new_addr = new_addr % self.table_size  # 如果获得新地址大于哈希表的实际大小
            #  就通过求余函数回环
            this_addr = new_addr
        return new_addr

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
            new_hashed_key = self.find_next_empty_addr(hashed_key) # 将新的节点插入到链表的头部 最新插入的数据最可能被用到
            # print("A")
        else:
            new_hashed_key = hashed_key
            # print("B")
        # print("debug 0:", new_hashed_key)
        self.table[new_hashed_key] = tmp
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
            query_offset_calc = NextAddr()
            while (tmp is not None) and tmp.keyword != keyword:
                hashed_key += query_offset_calc.get_next_query_offset()
                tmp = self.table[hashed_key]
            if tmp is None:
                print(keyword)
            return None if tmp is None else tmp
        else:  # 直接就没有找到 直接就返回None
            print(keyword)
            return None

    def rehash(self):
        """
        当超过哈希表的装填因子lambda时 触发再哈希
        :return:
        """
        print("rehash invoked!", self.table_size)
        new_hash_table = HashTable_OpenAddr(table_size=2*self.table_size, hash_function=self.hash_fun)
        for keyword_head in self.table:
            tmp = keyword_head
            if tmp is not None:
                new_hash_table.insert(keyword=tmp.keyword,val=tmp.val)
        self.table = new_hash_table.table
        self.table_size = new_hash_table.table_size
        self.hashed_size = new_hash_table.hashed_size

class HashTable_tb2:
    "testbench"
    def __init__(self):
        self.testcase_max_value = 500000
        self.testcase_max_length = 200000

    def gen_test_case(self):
        keys = set([random.randint(0, self.testcase_max_value) for i in range(1, self.testcase_max_length)])
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
        f = open("Hashtable_testcase2.txt", "w", encoding="utf-8")
        for case in testcases:
            f.write("\t".join((str(case[0]),case[1],"\n")))
        f.close()

    def load_testcase(self):
        testcases = []
        f = open("Hashtable_testcase2.txt", "r", encoding="utf-8")
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
                s_hashtable = HashTable_OpenAddr()
                for case in testcase1:
                    s_hashtable.insert(keyword=case[0], val=case[1])
                for case in testcase1:
                    # print(case)
                    assert  s_hashtable.query(keyword=case[0]).val == case[1], str(case[0])+":"+case[1]+":"+s_hashtable.query(keyword=case[0]).val
                # 创建一些不存在的关键字的查找
                for i in range(self.testcase_max_value+1, self.testcase_max_value+100):
                    assert s_hashtable.query(keyword=i) is None, str("query error")
# Test
if __name__ == "__main__":
    t = HashTable_tb2()
    t.main_testbench()




